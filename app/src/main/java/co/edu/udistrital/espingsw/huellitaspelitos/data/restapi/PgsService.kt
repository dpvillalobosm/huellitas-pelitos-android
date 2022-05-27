package co.edu.udistrital.espingsw.huellitaspelitos.data.restapi

import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.*
import retrofit2.http.*

interface PgsService {

    @POST("user/login")
    suspend fun login(@Body login: LoginDto): UserDto

    @GET("http://192.168.100.64:8080/api/v1/pet/user/{id}")
    suspend fun getPetsByUser(@Path("id") id: Int): List<PetDto>

    @GET("http://192.168.100.64:8080/api/v1/pet/{id}")
    suspend fun getPetById(@Path("id") id: Int): PetDto

    @GET("http://192.168.100.64:8082/api/v1/service")
    suspend fun getServices(): List<ServiceDto>

    @GET("user/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserDto

    @POST("http://192.168.100.64:8080/api/v1/pet/")
    suspend fun createPet(@Body pet: PetDto): PetDto

    @DELETE("http://192.168.100.64:8080/api/v1/pet/{id}")
    suspend fun deletePet(@Path("id") id: Int)

    @POST("user/")
    suspend fun createUser(@Body user: UserDto): UserDto

    @POST("http://192.168.100.64:8084/api/v1/clients/")
    suspend fun createClient(@Body client: ClientDto): ClientDto

    @POST("http://192.168.100.64:8081/api/v1/visit/")
    suspend fun createVisit(@Body visit: VisitDto): VisitDto

    @GET("http://192.168.100.64:8081/api/v1/visit/{id}")
    suspend fun getVisit(@Path("id") id: Int): VisitDto

    @GET("http://192.168.100.64:8084/api/v1/clients/{id}")
    suspend fun getClientById(@Path("id") id: Int): ClientDto

    @PUT("http://192.168.100.64:8081/api/v1/visit/")
    suspend fun updateVisit(@Body visit: VisitDto): VisitDto
}