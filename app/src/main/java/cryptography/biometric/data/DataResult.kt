package cryptography.biometric.data

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class DataResult<out R> {

    data class Success<out T>(val data: T) : DataResult<T>()
    data class Error(val exception: Exception) : DataResult<Nothing>()
    object Loading : DataResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val DataResult<*>.succeeded
    get() = this is DataResult.Success && data != null