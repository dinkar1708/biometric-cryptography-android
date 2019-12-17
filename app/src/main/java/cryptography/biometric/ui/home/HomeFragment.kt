package cryptography.biometric.ui.home


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import cryptography.biometric.R
import cryptography.biometric.databinding.FragmentHomeBinding
import cryptography.biometric.shared.BaseFragment
import cryptography.biometric.shared.setTitle
import cryptography.biometric.shared.showToast
import cryptography.biometric.shared.showToastOnUi
import cryptography.biometric.ui.home.data.GetUserTokenRequest
import cryptography.biometric.ui.home.data.GetUserTokenResponse
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.progress_layout.*
import timber.log.Timber
import javax.inject.Inject

/**
 */
class HomeFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HomeFragmentViewModel> { viewModelFactory }
    private lateinit var viewDataBinding: FragmentHomeBinding

    private var userTokenResponse: GetUserTokenResponse? = null

    // TODO just to show progress, not required
    private var handler: Handler = Handler()
    private var runnable: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setTitle(
            title = R.string.home_title, isEnableBackButton = false
        )

        viewDataBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home,
                container,
                false
            )
        // Bind layout with ViewModel
        viewDataBinding.viewModel = viewModel

        // Inflate the layout for this fragment
        viewDataBinding.next.setOnClickListener {
            if (viewDataBinding.amount.text.toString().trim().isEmpty()) {
                showToast(getString(R.string.home_enter_amount))
                return@setOnClickListener
            }

            userTokenResponse?.let {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToBiometricCryptographyPayment(
                        amount = amount.text.toString().trim(),
                        cardLast4Digit = it.cardLast4Digit,
                        userId = viewDataBinding.spinner.selectedItem as String,
                        token = it.token
                    )
                )
            }
        }

        setUpModelView()

        initSpinner()

        return viewDataBinding.root
    }

    private fun setUpModelView() {
        viewModel.getResultLiveDataState().observe(viewLifecycleOwner, Observer {
            val result = it ?: return@Observer
            Timber.d("result $result")
            userTokenResponse = result.successObject
            when {
                result.loading != null -> {
                    progress_layout.visibility = View.VISIBLE
                    progress_message.text = getString(R.string.home_receiving_token_from_server)
                }
                userTokenResponse != null -> {
                    activity?.runOnUiThread {
                        runnable = Runnable {
                            showToastOnUi(
                                String.format(
                                    getString(R.string.home_biometric_ap_token_received),
                                    userTokenResponse!!.cardLast4Digit
                                )
                            )
                            progress_layout.visibility = View.GONE
                            viewDataBinding.next.isEnabled = true
                        }
                        handler.postDelayed(
                            runnable!!,
                            1 * 1000
                        )
                    }
                }
                else -> {

                    progress_layout.visibility = View.GONE
                    result.error?.let { desc -> showToastOnUi(desc) }
                }
            }
        })
    }

    /**
     * Initialize the spinner
     */
    private fun initSpinner() {
        // dummy values used - this should be generated using User.kt file
        arrayOf("Bob", "Alice", "Tim", "Josh", "").apply {
            ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, this).apply {
                this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                viewDataBinding.spinner.adapter = this
                viewDataBinding.spinner.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                        }

                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            viewModel.getUserToken(GetUserTokenRequest(userId = viewDataBinding.spinner.selectedItem as String))
                        }

                    }
            }
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
