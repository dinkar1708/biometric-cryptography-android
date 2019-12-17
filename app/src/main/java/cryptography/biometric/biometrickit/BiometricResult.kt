package cryptography.biometric.biometrickit

import java.security.Signature
import javax.crypto.Cipher
import javax.crypto.Mac

/**
 * Data model for biometric result.
 * Error message stores the error during biometric authentication
 */
data class BiometricResult(
    var errorMessage: String? = null,
    var signature: Signature? = null,
    var cipher: Cipher? = null,
    var mac: Mac? = null
)