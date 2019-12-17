package cryptography.biometric.biometrickit.cryptography

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import cryptography.biometric.biometrickit.cryptography.CryptographySignatureBaseInterface.Companion.ANDROID_KEYSTORE
import cryptography.biometric.biometrickit.encodingdecoding.Base64Encoder
import timber.log.Timber
import java.security.*
import java.security.spec.ECGenParameterSpec
import java.util.*

class SignatureCryptography : CryptographySignatureInterface {

    override fun initSignature(keyName: String): Signature? {
        getAsymmetricKey(
            keyName
        )?.let {
            Signature.getInstance("SHA256withECDSA").apply {
                this.initSign(it.private)
                return this
            }
        }
        return null
    }

    /**
     * Generate asymmetric key
     */
    override fun generateAsymmetricKey(
        keyName: String,
        isUserAuthenticationRequired: Boolean
    ): KeyPair? {

        try {
            KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_EC, ANDROID_KEYSTORE).run {

                val builder = KeyGenParameterSpec.Builder(
                    keyName,
                    KeyProperties.PURPOSE_SIGN
                )
                    .setAlgorithmParameterSpec(ECGenParameterSpec("secp256r1"))
                    .setDigests(
                        KeyProperties.DIGEST_SHA256,
                        KeyProperties.DIGEST_SHA384,
                        KeyProperties.DIGEST_SHA512
                    )
                    .setUserAuthenticationRequired(isUserAuthenticationRequired)

                if (Build.VERSION.SDK_INT >= 24) {
                    builder.setInvalidatedByBiometricEnrollment(isUserAuthenticationRequired)
                }

                this.initialize(builder.build())

                this.generateKeyPair()
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
        return null
    }

    /**
     * Find asymmetric key by given name
     */
    override fun getAsymmetricKey(keyName: String): KeyPair? {
        KeyStore.getInstance(ANDROID_KEYSTORE).apply {
            this.load(null)
            if (this.containsAlias(keyName)) {
                // Get public key
                val publicKey = this.getCertificate(keyName).publicKey
                // Get private key
                val privateKey = this.getKey(keyName, null) as PrivateKey
                // Return a key pair
                return KeyPair(publicKey, privateKey)
            }
        }
        return null
    }

    /**
     * Sign the message using signature object
     */
    override fun signMessage(signature: Signature, message: ByteArray): ByteArray {
        signature.update(message).apply {
            return signature.sign()
        }
    }

    /**
     * encoding decoding
     */
    override fun encoder(): Base64Encoder {
        return Base64Encoder()
    }

    companion object {
        // 32 byte nonce
        val KEY_32ByteNonce = UUID.randomUUID().toString()
    }

}