package co.edu.udistrital.espingsw.huellitaspelitos.data.repository

import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.UserDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.UserDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.ApiCallResult
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDataSource: UserDataSource) {
    suspend fun getUserById(userId: Int): UserDto? {
        val result = userDataSource.getUserById(userId)

        return if(result is ApiCallResult.Success){
            result.data
        } else {
            null
        }
    }

    suspend fun createUser(user: UserDto): UserDto? {
        val result = userDataSource.createUser(user)

        return if(result is ApiCallResult.Success){
            result.data
        } else {
            null
        }
    }
}