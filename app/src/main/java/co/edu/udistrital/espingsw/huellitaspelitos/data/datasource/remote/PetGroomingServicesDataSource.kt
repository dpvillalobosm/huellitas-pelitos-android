package co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote

import android.util.Log
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.PgsService
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.ServiceDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.ApiCallResult
import java.io.IOException
import javax.inject.Inject

class PetGroomingServicesDataSource @Inject constructor(private val apiService: PgsService) {
    suspend fun getAllPetGroomingServices(): ApiCallResult<List<ServiceDto>> {
        return try{
            val response = apiService.getServices()
            ApiCallResult.Success(response)
        } catch (e: Throwable){
            Log.e("PetGroomingServicesDS", "Error retrofit", e)
            ApiCallResult.Error(IOException("Error getting pet by id", e))
        }
    }
}