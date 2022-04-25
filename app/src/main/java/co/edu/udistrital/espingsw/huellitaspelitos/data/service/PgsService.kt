package co.edu.udistrital.espingsw.huellitaspelitos.data.service

import co.edu.udistrital.espingsw.huellitaspelitos.data.service.dto.LoginDTO
import co.edu.udistrital.espingsw.huellitaspelitos.data.service.dto.UserDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface PgsService {

    @POST("user/login")
    suspend fun login(@Body login: LoginDTO): UserDTO
}