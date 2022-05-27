package co.edu.udistrital.espingsw.huellitaspelitos.data.repository

import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.PetGroomingServicesDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.ServiceDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.ApiCallResult
import javax.inject.Inject

class PetGroomingRepository @Inject constructor(private val petGroomingServicesDataSource: PetGroomingServicesDataSource) {
    suspend fun getAllServices(): List<ServiceDto>? {
        val result = petGroomingServicesDataSource.getAllPetGroomingServices()

        return if(result is ApiCallResult.Success){
            result.data
        } else {
            null
        }
    }
}