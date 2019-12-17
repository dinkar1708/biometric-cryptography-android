package cryptography.biometric.data.api

/**
 * State of API, loading, error and on success it should capture result in success object.
 */
data class ApiState<T>(
    val loading: Boolean? = null,
    val successObject: T? = null,
    // server error message
    val error: String? = null
)
