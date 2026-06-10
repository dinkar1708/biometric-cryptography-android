package cryptography.biometric.ui.main.verification


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cryptography.biometric.R
import cryptography.biometric.databinding.FragmentPaymentVerificationBinding
import cryptography.biometric.ext.setTitle
import cryptography.biometric.shared.BaseFragment

/**
 * Payment and signature verification all is done, now display the transaction details.
 */
class PaymentVerificationFragment : BaseFragment() {

    private val args: PaymentVerificationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setTitle(title = R.string.payment_verification_title, isEnableBackButton = false)

        // Inflate the layout for this fragment using ViewBinding
        val binding = FragmentPaymentVerificationBinding.inflate(inflater, container, false)
        binding.amount.text = String.format(getString(R.string.amount_format), args.amount)
        binding.cardNumber.text =
            String.format(getString(R.string.card_number_format), args.cardLast4Digit)
        binding.userId.text = args.userId

        binding.done.setOnClickListener {
            findNavController().navigate(R.id.action_paymentVerificationFragment_to_homeFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback {
            activity?.finish()
        }

        return binding.root
    }
}
