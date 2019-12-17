package cryptography.biometric.biometrickit.encodingdecoding

import android.util.Base64

/**
 * encoding decoding data
 */
interface Encoder {
    fun encode(messageByteArray: ByteArray): String
    fun decode(messageString: String): ByteArray
}

/**
 * Base 64 format encoding decoding
 */
class Base64Encoder : Encoder {
    override fun encode(messageByteArray: ByteArray): String =
        Base64.encodeToString(messageByteArray, Base64.URL_SAFE)

    override fun decode(messageString: String): ByteArray =
        Base64.decode(messageString, Base64.URL_SAFE)
}