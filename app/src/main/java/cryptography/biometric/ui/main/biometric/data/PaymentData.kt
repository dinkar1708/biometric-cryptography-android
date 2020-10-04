package cryptography.biometric.ui.main.biometric.data

/**
 * Data model to be sent on server for verification
 */
data class PaymentData(
    // cryptographic algorithim example - ec - asymmetric algorithm
    val protocolVersion: String,
    // signature message, encrypted message
    val paymentMessage: ByteArray,
    // public key
    val signedMessage: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PaymentData

        if (protocolVersion != other.protocolVersion) return false
        if (!paymentMessage.contentEquals(other.paymentMessage)) return false
        if (!signedMessage.contentEquals(other.signedMessage)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = protocolVersion.hashCode()
        result = 31 * result + paymentMessage.contentHashCode()
        result = 31 * result + signedMessage.contentHashCode()
        return result
    }
}

/**
 * Payment data
 */
data class PaymentMessage(

    val userToken: String,
//     public key of message signed
    val ephemeralPublicKey: String,
//    A Base64-encoded encrypted message that contains payment information and some additional
    val encryptedMessage: String,
//    32 bytes random nonce
    val tagNonce32Byte: String
)
