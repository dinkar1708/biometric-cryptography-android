package cryptography.biometric.data.source.remote

import cryptography.biometric.data.DataResult
import cryptography.biometric.data.api.ApiBaseResponse
import cryptography.biometric.data.api.RetrofitAPIService
import cryptography.biometric.data.source.CryptographyDataSource
import cryptography.biometric.server.FakeServer
import cryptography.biometric.ui.biometric.data.VerifySignatureRequest
import cryptography.biometric.ui.biometric.data.VerifySignatureResponse
import cryptography.biometric.ui.home.data.GetUserTokenRequest
import cryptography.biometric.ui.home.data.GetUserTokenResponse
import java.security.PublicKey
import java.util.*

/**
 * Implementation of the data source that adds a latency simulating network.
 */
class CryptographyRemoteDataSource constructor(private val networkInterface: RetrofitAPIService) :
    CryptographyDataSource {

    private var fakeServer: FakeServer = FakeServer()

    override suspend fun getUserToken(request: GetUserTokenRequest): DataResult<GetUserTokenResponse> {
//        return RetrofitAPIService.getResult { networkInterface.getUserToken(request) }

        val res = GetUserTokenResponse(
            token = UUID.randomUUID().toString(),
            // just dummy 4 digit
            cardLast4Digit = (Random().nextInt(8999) + 1000).toString()
        )
        res.status = true

        return DataResult.Success(
            res
        )
    }


    /**
     * Send public key on server
     */
    override suspend fun sendPublicKey(request: PublicKey): DataResult<ApiBaseResponse> {
        // this should be actual code to send token on server
        // store public key, just using PublicKey
        // but it could be base 64 to be send on actual server CryptographyAuth.base64Encode(request.encoded)
//        return RetrofitAPIService.getResult { networkInterface.sendPublicKey(CryptographyAuth.base64Encode(request.encoded)) }
        // TODO hack to send dummy response
        fakeServer.enrollPublicKey(request)
        return DataResult.Success(ApiBaseResponse(status = true))
    }

    /**
     * verify signature
     */
    override suspend fun verifySignature(request: VerifySignatureRequest): DataResult<VerifySignatureResponse> {
        // TODO actual code
//        return RetrofitAPIService.getResult { networkInterface.verifySignature(request) }
        // hack to verify signature
        val response = fakeServer.verifySignature(request.paymentMessage)
        return if (response == null) {
            val verifySignatureResponse =
                VerifySignatureResponse(response = "Signature Verification Done!")
            verifySignatureResponse.status = true
            DataResult.Success(verifySignatureResponse)
        } else {
            DataResult.Error(Exception(response))
        }
    }
}
