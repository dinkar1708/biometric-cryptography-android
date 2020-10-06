package cryptography.biometric.biometrickit.cryptography

/**
 * Supported by android keystore
 *  Android doesn't guarantee a particular provider for a given algorithm. Specifying a provider
 *  without using the Android Keystore system could cause compatibility problems in future releases.
 * https://developer.android.com/guide/topics/security/cryptography
 */
interface CryptographyProvider {
    //    AES in either CBC or GCM mode with 256-bit keys (such as AES/GCM/NoPadding)
    fun cipher()

    //    SHA-2 family (eg, SHA-256)
    fun messageDigest()

    //    SHA-2 family HMAC (eg, HMACSHA256)
    fun mac()

    //    SHA-2 family with ECDSA (eg, SHA256withECDSA)
    fun signature(): SignatureCryptography
}

class CryptographyTechnique : CryptographyProvider {
    override fun cipher() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun messageDigest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mac() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signature(): SignatureCryptography {
        return SignatureCryptography()
    }

}