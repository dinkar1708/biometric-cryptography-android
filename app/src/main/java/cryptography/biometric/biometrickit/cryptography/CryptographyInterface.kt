package cryptography.biometric.biometrickit.cryptography

import cryptography.biometric.biometrickit.encodingdecoding.Base64Encoder
import java.security.KeyPair
import java.security.Signature
import javax.crypto.Cipher
import javax.crypto.SecretKey

/**
 * Base interface for cryptography authentication
 */
interface CryptographySignatureBaseInterface {
    companion object {
        // android key store system
        const val ANDROID_KEYSTORE = "AndroidKeyStore"
    }
}

/**
 * Available interface for authentication
 * it can be 3 kinds of authentication mechanics.
 */
interface CryptographySignatureInterface : CryptographySignatureBaseInterface {
    /**
     * initialize signature
     */
    fun initSignature(keyName: String): Signature?

    /**
     * generate asymmetric key pair
     */
    fun generateAsymmetricKey(
        keyName: String,
        isUserAuthenticationRequired: Boolean
    ): KeyPair?

    /**
     * find asymmetric key pair
     */
    fun getAsymmetricKey(keyName: String): KeyPair?

    /**
     * sing the message byte array using signature
     */
    fun signMessage(signature: Signature, message: ByteArray): ByteArray

    /**
     * this is encoding decoding encoder
     */
    fun encoder(): Base64Encoder

}

/**
 * Can be use in future
 */
interface CryptographyCipherInterface : CryptographySignatureBaseInterface {

    fun initSignature(keyName: String): Cipher

    fun createKey(
        keyName: String,
        invalidatedByBiometricEnrollment: SecretKey
    )

    fun getKey(keyName: String): SecretKey

}