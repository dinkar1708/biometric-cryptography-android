package cryptography.biometric.data.source

import cryptography.biometric.data.DataResult
import cryptography.biometric.data.api.ApiBaseResponse
import cryptography.biometric.ui.biometric.data.VerifySignatureRequest
import cryptography.biometric.ui.biometric.data.VerifySignatureResponse
import cryptography.biometric.ui.home.data.GetUserTokenRequest
import cryptography.biometric.ui.home.data.GetUserTokenResponse
import java.security.PublicKey

/**
 * Main entry point for accessing tasks data. it could be either local or remote
 */
interface CryptographyDataSource {

    suspend fun getUserToken(request: GetUserTokenRequest): DataResult<GetUserTokenResponse>

    suspend fun sendPublicKey(request: PublicKey): DataResult<ApiBaseResponse>

    suspend fun verifySignature(request: VerifySignatureRequest): DataResult<VerifySignatureResponse>


}
