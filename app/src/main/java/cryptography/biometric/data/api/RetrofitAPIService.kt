package cryptography.biometric.data.api

import com.google.gson.GsonBuilder
import cryptography.biometric.BuildConfig
import cryptography.biometric.data.DataResult
import cryptography.biometric.ui.main.biometric.data.VerifySignatureRequest
import cryptography.biometric.ui.main.biometric.data.VerifySignatureResponse
import cryptography.biometric.ui.main.home.data.GetUserTokenRequest
import cryptography.biometric.ui.main.home.data.GetUserTokenResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import timber.log.Timber
import java.util.concurrent.TimeUnit


/**
 * FakeServer interface for now.
 */
interface RetrofitAPIService {

    @POST("/getUserToken")
    suspend fun getUserToken(@Body request: GetUserTokenRequest): Response<GetUserTokenResponse>

    @POST("/sendPublicKey")
    suspend fun sendPublicKey(@Body request: String): Response<ApiBaseResponse>

    @POST("/verifySignature")
    suspend fun verifySignature(@Body request: VerifySignatureRequest): Response<VerifySignatureResponse>

    companion object {
        /**
         * Get api data in background thread
         */
        suspend fun <T> getResult(call: suspend () -> Response<T>): DataResult<T> {
            return try {
                val response = call()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) return DataResult.Success(body)
                }

                error(" ${response.code()} ${response.message()}")

            } catch (e: Exception) {
                error(e)
            }
        }

        /**
         * log the error
         */
        private fun <T> error(message: java.lang.Exception): DataResult<T> {
            Timber.e(message)
            return DataResult.Error(message)
        }

        private val BASE_URL: String
            get() = BuildConfig.BASE_URL

        /**
         * this is private method which create the retrofit instance with default settings
         */
        fun create(): RetrofitAPIService {
            return getRetrofit().create(RetrofitAPIService::class.java)
        }

        fun getRetrofit(): Retrofit {

            HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
                val builder = OkHttpClient.Builder()
                builder.addInterceptor(this)
                    .connectTimeout(50, TimeUnit.SECONDS)
                    .readTimeout(40, TimeUnit.SECONDS)
                    .followRedirects(true)
                    .followSslRedirects(true)
                return Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder()
                                .create()
                        )
                    )
                    .baseUrl(BASE_URL)
                    .client(builder.build())
                    .build()
            }
        }
    }
}