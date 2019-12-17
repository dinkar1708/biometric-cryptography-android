package cryptography.biometric.data.api

/**
 * Retrofit API base response model
 */
open class ApiBaseResponse(
    var description: String? = null,
    var status: Boolean = false
)