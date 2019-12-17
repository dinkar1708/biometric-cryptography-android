package cryptography.biometric.biometrickit

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.fragment.app.Fragment
import cryptography.biometric.R

/**
 * Check if can authenticate using biometric
 */
fun Fragment.canAuthenticateUsingBiometric(): String? {
    return when (canAuthenticate(context!!)) {
        BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
            getString(R.string.biometric_dialog_hardware_not_supported)
        }
        BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
            getString(R.string.biometric_dialog_finger_not_enrolled)
        }
        else -> {
            null
        }
    }
}

/**
 * check if biometric authentication can be done.
 */
fun canAuthenticate(context: Context): Int {
    return BiometricManager.from(context).canAuthenticate()
}