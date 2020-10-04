package cryptography.biometric.ui.verification


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cryptography.biometric.R
import cryptography.biometric.ext.setTitle
import cryptography.biometric.shared.BaseFragment
import kotlinx.android.synthetic.main.fragment_payment_verification.view.*

/**
 * Payment and signature verification all is done, now display the transaction details.
 */
class PaymentVerificationFragment : BaseFragment() {

    private val args: PaymentVerificationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setTitle(title = R.string.payment_verification_title, isEnableBackButton = false)

        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_payment_verification, container, false)
        v.amount.text = String.format(getString(R.string.amount_format), args.amount)
        v.card_number.text =
            String.format(getString(R.string.card_number_format), args.cardLast4Digit)
        v.user_id.text = args.userId

        v.done.setOnClickListener {
            findNavController().navigate(R.id.action_paymentVerificationFragment_to_homeFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback {
            activity?.finish()
        }

        return v
    }
}
