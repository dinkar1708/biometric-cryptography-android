package cryptography.biometric.server

import com.google.gson.Gson
import cryptography.biometric.biometrickit.encodingdecoding.Base64Encoder
import cryptography.biometric.ui.biometric.data.PaymentData
import cryptography.biometric.ui.biometric.data.PaymentMessage
import timber.log.Timber
import java.security.InvalidKeyException
import java.security.PublicKey
import java.security.Signature
import java.security.SignatureException


/**
 * To simulate the signature verification
 */
class FakeServer {
    private var publicKey: PublicKey? = null

    /**
     * Get public key from client
     */
    fun enrollPublicKey(publicKey: PublicKey) {
        this.publicKey = publicKey
    }

    /**
     * Verify the signature message using public key
     */
    fun verifySignature(paymentData: PaymentData): String? {
        if (this.publicKey == null) {
            Timber.e("verifySignatureNot enrolled yet!!!!")
            return "verifySignatureNot enrolled yet!!!!"
        }
        try {
            Signature.getInstance("SHA256withECDSA").apply {
                // init verification using public key
                this.initVerify(publicKey)
                // update the signature message
                this.update(paymentData.paymentMessage)
                // verify the signed message
                if (this.verify(paymentData.signedMessage)) {
                    // verification is success
                    Timber.d("verifySignature done")
                    // this is just to get the data of payment
                    val msg =
                        Gson().fromJson(
                            String(paymentData.paymentMessage),
                            PaymentMessage::class.java
                        )
                    Timber.d(
                        "verifySignature data original value ${String(
                            Base64Encoder().decode(
                                msg.encryptedMessage
                            )
                        )}"
                    )
                    return null
                } else {
                    Timber.d("verifySignature verification Failed!!!")
                }
            }
        } catch (e: InvalidKeyException) {
            Timber.e(e)
            return e.localizedMessage
        } catch (e: SignatureException) {
            Timber.e(e)
            return e.localizedMessage
        } catch (e: Exception) {
            Timber.e(e)
            return e.localizedMessage
        }

        return "Unexpected error while verifying signature..."
    }
}