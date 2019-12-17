package cryptography.biometric.data.source

import cryptography.biometric.data.DataResult
import cryptography.biometric.data.api.ApiBaseResponse
import cryptography.biometric.di.ApplicationModule.CryptographyRemoteDataSource
import cryptography.biometric.server.FakeServer
import cryptography.biometric.ui.biometric.data.VerifySignatureRequest
import cryptography.biometric.ui.biometric.data.VerifySignatureResponse
import cryptography.biometric.ui.home.data.GetUserTokenRequest
import cryptography.biometric.ui.home.data.GetUserTokenResponse
import java.security.PublicKey
import javax.inject.Inject


/**
 * Concrete implementation to load tasks from the data sources into a cache.
 *
 * To simplify the sample, this repository only uses the local data source only if the remote
 * data source fails. Remote is the source of truth.
 */
class CryptographyDafaultRepository @Inject constructor(
    @CryptographyRemoteDataSource private val tasksRemoteDataSource: CryptographyDataSource
) : CryptographyRepository {
    override suspend fun getUserToken(request: GetUserTokenRequest): DataResult<GetUserTokenResponse> {
        return tasksRemoteDataSource.getUserToken(request)
    }

    /**
     * TODO delete it later
     * Hack to display server has actually stored the public key and is verifying our request
     */
    override var fakeServer: FakeServer = FakeServer()

    override suspend fun sendPublicKey(request: PublicKey): DataResult<ApiBaseResponse> {
        return tasksRemoteDataSource.sendPublicKey(request)
    }


    override suspend fun verifySignature(request: VerifySignatureRequest): DataResult<VerifySignatureResponse> {
        return tasksRemoteDataSource.verifySignature(request)
    }
}
