package co.edu.udistrital.espingsw.huellitaspelitos.data

import android.util.Log
import co.edu.udistrital.espingsw.huellitaspelitos.data.model.LoggedInUser
import co.edu.udistrital.espingsw.huellitaspelitos.data.service.PgsService
import co.edu.udistrital.espingsw.huellitaspelitos.data.service.dto.LoginDTO
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource(private val apiService: PgsService) {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        return try {

            val login = LoginDTO(username, password)

            val response = apiService.login(login)

            Log.d("LoginDataSource", response.phone.toString())

            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            Result.Success(fakeUser)
        } catch (e: Throwable) {
            Log.e("LoginDataSource", "Error retrofit", e)
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}