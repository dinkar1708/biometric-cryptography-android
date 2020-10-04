package cryptography.biometric.ui.biometric


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import cryptography.biometric.R
import cryptography.biometric.biometrickit.BiometricDialog
import cryptography.biometric.biometrickit.canAuthenticateUsingBiometric
import cryptography.biometric.biometrickit.cryptography.CryptographyTechnique
import cryptography.biometric.biometrickit.cryptography.SignatureCryptography.Companion.KEY_32ByteNonce
import cryptography.biometric.databinding.FragmentBiometricCryptographyPaymentBinding
import cryptography.biometric.ext.setTitle
import cryptography.biometric.ext.showToast
import cryptography.biometric.ext.showToastOnUi
import cryptography.biometric.shared.BaseFragment
import cryptography.biometric.ui.biometric.data.PaymentData
import cryptography.biometric.ui.biometric.data.PaymentMessage
import cryptography.biometric.ui.biometric.data.VerifySignatureRequest
import cryptography.biometric.viewmodels.ViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_biometric_cryptography_payment.*
import kotlinx.android.synthetic.main.fragment_biometric_cryptography_payment.view.*
import kotlinx.android.synthetic.main.progress_layout.*
import timber.log.Timber
import java.security.KeyPair
import javax.inject.Inject

/**
 */
class BiometricCryptographyPaymentFragment : BaseFragment() {

    private val args: BiometricCryptographyPaymentFragmentArgs by navArgs()

    private lateinit var paymentMessage: PaymentMessage
    private var asymmetricKey: KeyPair? = null

    @Inject
    lateinit var biometricDialog: BiometricDialog

    @Inject
    lateinit var cryptographyTechnique: CryptographyTechnique


    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private val viewModel by viewModels<BiometricCryptographyPaymentViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: FragmentBiometricCryptographyPaymentBinding

    // TODO just to show progress, not required
    private var handler: Handler = Handler()
    private var runnable: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setTitle(title = R.string.biometric_ap_title)

        viewDataBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_biometric_cryptography_payment,
                container,
                false
            )
//        // Bind layout with ViewModel
        viewDataBinding.viewModel = viewModel

        viewDataBinding.amount.text = String.format(getString(R.string.amount_format), args.amount)
        viewDataBinding.cardNumber.setText(
            String.format(getString(R.string.card_number_format), args.cardLast4Digit)
        )

        val msg = canAuthenticateUsingBiometric()
        if (msg != null) {
            showToast(msg)
            activity?.onBackPressed()
            return null
        }

        initBiometric()

        setUpModelView()

        // store public key
        asymmetricKey?.let {
            viewModel.storePublicKey(it.public)
        }

        return viewDataBinding.root
    }

    private fun setUpModelView() {
        viewModel.getResultLiveDataState().observe(viewLifecycleOwner, Observer {
            val result = it ?: return@Observer
            when {
                result.loading != null -> {
                    progress_layout.visibility = View.VISIBLE
                    progress_message.text = getString(R.string.biometric_ap_verifying_signature)
                }
                result.successObject != null -> {
                    // TODO remove it - this is just to display little late response from server
                    runnable = Runnable {
                        showToastOnUi(result.successObject.response)
                        findNavController().navigate(
                            BiometricCryptographyPaymentFragmentDirections.actionBiometricCryptographyPaymentToPaymentVerificationFragment2(
                                cardLast4Digit = args.cardLast4Digit,
                                userId = args.userId,
                                amount = args.amount
                            )
                        )
                    }

                    handler.postDelayed(
                        runnable!!,
                        2000
                    )

                }
                else -> {

                    progress_layout.visibility = View.GONE
                    result.error?.let { desc -> showToastOnUi(desc) }
                }
            }
        })
    }

    private fun initBiometric() {
        // build the title of biometric dialog
        biometricDialog.buildTitle(
            title = getString(R.string.biometric_dialog_title),
            subTitle = getString(R.string.biometric_dialog_subtitle),
            description = getString(R.string.biometric_dialog_desc),
            negativeButtonText = getString(R.string.biometric_dialog_negative_button)
        )

        asymmetricKey =
            cryptographyTechnique.signature().generateAsymmetricKey(KEY_32ByteNonce, true)
        if (asymmetricKey == null) {
            showToast(getString(R.string.unexpected_error_can_not_generate_keypair))
            activity?.onBackPressed()
            return
        }

        val initSignature = cryptographyTechnique.signature().initSignature(KEY_32ByteNonce)

        paymentMessage = PaymentMessage(
            userToken = args.token,
            ephemeralPublicKey = cryptographyTechnique.signature().encoder()
                .encode(asymmetricKey!!.public.encoded),
            tagNonce32Byte = KEY_32ByteNonce,
            encryptedMessage = cryptographyTechnique.signature().encoder()
                .encode(viewDataBinding.amount.text.toString().toByteArray())
        )

        // Inflate the layout for this fragment
        viewDataBinding.root.auth.setOnClickListener {
            progress_layout.visibility = View.VISIBLE
            progress_message.text =
                getString((R.string.biometric_ap_biometric_authenticate_in_progress))
            biometricDialog.doBiometricAuthenticationBiometricPrompt(
                activity!!,
                signature = initSignature
            )
                .subscribe({
                    Timber.d("doBiometricAuthenticationBiometricPrompt $it")
                    when {
                        // handle error message
                        it.errorMessage != null -> {
                            activity?.runOnUiThread {
                                progress_layout.visibility = View.GONE
                                showToastOnUi(it.errorMessage!!)
                            }
                        }
                        // success case
                        it.signature != null -> {
                            // Do not click now everything is completed
                            showToastOnUi(getString(R.string.biometric_ap_auth_success))
                            // TODO remove it just to show progress
                            activity?.runOnUiThread {
                                viewDataBinding.root.auth.isEnabled = false
                                runnable = Runnable {
                                    val dataByteArray = Gson().toJson(paymentMessage).toByteArray()
                                    // sign the message now, using signature after authentication using
                                    // touch device
                                    val signedByteArray =
                                        cryptographyTechnique.signature()
                                            .signMessage(it.signature!!, dataByteArray)
                                    // verify signed message, this should be verify on server
                                    viewModel.verifyMessage(
                                        VerifySignatureRequest(
                                            PaymentData(
                                                protocolVersion = "EC",
                                                signedMessage = signedByteArray,
                                                paymentMessage = dataByteArray
                                            )
                                        )
                                    )

                                }
                                handler.postDelayed(
                                    runnable!!,
                                    2000
                                )
                            }
                        }

                    }
                }, {
                    Timber.e(it)
                })

        }
    }

    override fun onPause() {
        super.onPause()
        // handler null error
        runnable?.let {
            handler.removeCallbacks(it)
        }
    }
}
