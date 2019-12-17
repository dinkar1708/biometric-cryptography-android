package cryptography.biometric.data.source

import cryptography.biometric.data.DataResult
import cryptography.biometric.data.api.ApiBaseResponse
import cryptography.biometric.server.FakeServer
import cryptography.biometric.ui.biometric.data.VerifySignatureRequest
import cryptography.biometric.ui.biometric.data.VerifySignatureResponse
import cryptography.biometric.ui.home.data.GetUserTokenRequest
import cryptography.biometric.ui.home.data.GetUserTokenResponse
import java.security.PublicKey

/**
 * Interface to the data layer.
 */
interface CryptographyRepository {

    var fakeServer: FakeServer

    suspend fun getUserToken(request: GetUserTokenRequest): DataResult<GetUserTokenResponse>

    suspend fun sendPublicKey(request: PublicKey): DataResult<ApiBaseResponse>

    suspend fun verifySignature(request: VerifySignatureRequest): DataResult<VerifySignatureResponse>

}
