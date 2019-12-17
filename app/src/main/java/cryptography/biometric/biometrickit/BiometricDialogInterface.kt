package cryptography.biometric.biometrickit

import androidx.fragment.app.FragmentActivity
import io.reactivex.Observable
import java.security.Signature
import javax.crypto.Cipher
import javax.crypto.Mac

/**
 * Biometric authentication interface
 */
interface BiometricDialogInterface {

    /**
     * Text of dialog
     */
    fun buildTitle(
        title: CharSequence? = "",
        subTitle: CharSequence? = "",
        description: CharSequence? = "",
        negativeButtonText: CharSequence? = ""
    )

    /**
     * Perform biometric authenticate using Biometric Prompt Dialog
     */
    fun doBiometricAuthenticationBiometricPrompt(
        activity: FragmentActivity,
        signature: Signature? = null, cipher: Cipher? = null, mac: Mac? = null
    ): Observable<BiometricResult>

}
