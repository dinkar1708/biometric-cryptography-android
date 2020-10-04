package cryptography.biometric.biometrickit

import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import io.reactivex.Observable
import java.security.Signature
import java.util.concurrent.Executors
import javax.crypto.Cipher
import javax.crypto.Mac
import javax.inject.Inject

/**
 * Biometric authentication using this dialog.
 */
class BiometricDialog : BiometricDialogInterface {

    private var title: CharSequence = "Title"
    private var subTitle: CharSequence = "SubTitle"
    private var description: CharSequence = "Description"
    private var negativeButtonText: CharSequence = "Cancel"

    override fun buildTitle(
        title: CharSequence?,
        subTitle: CharSequence?,
        description: CharSequence?,
        negativeButtonText: CharSequence?
    ) {
        this.title = title!!
        this.subTitle = subTitle!!
        this.description = description!!
        this.negativeButtonText = negativeButtonText!!

    }

    /**
     * Authenticate using Biometric Prompt Dialog
     * Uses Rx java for sending the call back
     */
    override fun doBiometricAuthenticationBiometricPrompt(
        activity: FragmentActivity,
        signature: Signature?, cipher: Cipher?, mac: Mac?
    ): Observable<BiometricResult> {

        return Observable.create { subscriber ->
            val biometricPrompt =
                BiometricPrompt(activity, Executors.newSingleThreadExecutor(), object :
                    BiometricPrompt.AuthenticationCallback() {

                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        subscriber.onNext(
                            BiometricResult(
                                errorMessage = errString.toString()
                            )
                        )
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        subscriber.onNext(
                            BiometricResult(
                                signature = result.cryptoObject?.signature,
                                cipher = result.cryptoObject?.cipher,
                                mac = result.cryptoObject?.mac
                            )
                        )
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        subscriber.onNext(
                            BiometricResult(
                                errorMessage = biometricAuthFailed
                            )
                        )
                    }
                })

            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(title)
                .setSubtitle(subTitle)
                .setDescription(description)
                .setNegativeButtonText(negativeButtonText)
                .build()

            var cryptoObject: BiometricPrompt.CryptoObject? = null
            if (signature != null) cryptoObject = BiometricPrompt.CryptoObject(signature)
            if (cipher != null) cryptoObject = BiometricPrompt.CryptoObject(cipher)
            if (mac != null) cryptoObject = BiometricPrompt.CryptoObject(mac)
            if (cryptoObject != null) {
                biometricPrompt.authenticate(promptInfo, cryptoObject)
            } else {
                biometricPrompt.authenticate(promptInfo)
            }
        }
    }


    companion object {
        const val biometricAuthFailed: String = "Biometric is valid but not recognized!"
    }
}
