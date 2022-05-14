package co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote

import android.util.Log
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Result
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.PgsService
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.LoginDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.UserDto
import java.io.IOException
import javax.inject.Inject

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource @Inject constructor(private val apiService: PgsService) {

    suspend fun login(username: String, password: String): Result<UserDto> {
        return try {
            val login = LoginDto(username, password)
            val response = apiService.login(login)
            Result.Success(response)
        } catch (e: Throwable) {
            Log.e("LoginDataSource", "Error retrofit", e)
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}