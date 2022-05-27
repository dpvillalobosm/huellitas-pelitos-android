package co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote

import android.util.Log
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.ApiCallResult
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.PgsService
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.PetDto
import java.io.IOException
import javax.inject.Inject

class PetDataSource @Inject constructor(private val apiService: PgsService) {
    suspend fun getPetsByUser(idUser: Int): ApiCallResult<List<PetDto>> {
        return try{
            val response = apiService.getPetsByUser(idUser)
            ApiCallResult.Success(response)
        } catch(e: Throwable){
            Log.e("PetDataSource", "Error retrofit", e)
            ApiCallResult.Error(IOException("Error getting pets by user", e))
        }
    }

    suspend fun getPetById(idPet: Int): ApiCallResult<PetDto> {
        return try{
            val response = apiService.getPetById(idPet)
            ApiCallResult.Success(response)
        } catch(e: Throwable){
            Log.e("PetDataSource", "Error retrofit", e)
            ApiCallResult.Error(IOException("Error getting pet by id", e))
        }
    }

    suspend fun createPet(pet: PetDto): ApiCallResult<PetDto> {
        return try {
            val response = apiService.createPet(pet)
            ApiCallResult.Success(response)
        } catch (e: Throwable){
            Log.e("PetDataSource", "Error retrofit", e)
            ApiCallResult.Error(IOException("Error creating pet", e))
        }
    }

    suspend fun deletePet(petId: Int): ApiCallResult<Int> {
        return try{
            apiService.deletePet(petId)
            ApiCallResult.Success(1)
        } catch (e: Throwable){
            Log.e("PetDataSource", "Error retrofit", e)
            ApiCallResult.Error(IOException("Error deleting pet", e))
        }
    }
}