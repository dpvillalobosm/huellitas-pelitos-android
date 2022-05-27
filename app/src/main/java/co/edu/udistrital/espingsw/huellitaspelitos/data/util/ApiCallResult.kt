package co.edu.udistrital.espingsw.huellitaspelitos.data.util

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ApiCallResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : ApiCallResult<T>()
    data class Error(val exception: Exception) : ApiCallResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}