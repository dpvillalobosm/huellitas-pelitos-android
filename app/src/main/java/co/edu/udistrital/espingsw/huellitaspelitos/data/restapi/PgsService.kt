package co.edu.udistrital.espingsw.huellitaspelitos.data.restapi

import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.LoginDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.PetDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.UserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PgsService {

    @POST("user/login")
    suspend fun login(@Body login: LoginDto): UserDto

    @GET("http://pgspetsapi-env.eba-qsfhdwcv.us-east-1.elasticbeanstalk.com/api/v1/pet/user/{id}")
    suspend fun getPetsByUser(@Path("id") id: Int): List<PetDto>
}