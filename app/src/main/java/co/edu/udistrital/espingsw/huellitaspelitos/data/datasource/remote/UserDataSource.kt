package co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote

import android.util.Log
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.PgsService
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.UserDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.ApiCallResult
import java.io.IOException
import javax.inject.Inject

class UserDataSource @Inject constructor(private val apiService: PgsService) {

    suspend fun getUserById(userId: Int): ApiCallResult<UserDto> {
        return try{
            val response = apiService.getUserById(userId)
            ApiCallResult.Success(response)
        } catch(e: Throwable) {
            Log.e("UserDataSource", "Error retrofit", e)
            ApiCallResult.Error(IOException("Error getting user by id", e))
        }
    }

    suspend fun createUser(user: UserDto): ApiCallResult<UserDto> {
        return try {
            val response = apiService.createUser(user)
            ApiCallResult.Success(response)
        } catch (e: Throwable) {
            Log.e("UserDataSource", "Error retrofit", e)
            ApiCallResult.Error(IOException("Error creating user", e))
        }
    }

}