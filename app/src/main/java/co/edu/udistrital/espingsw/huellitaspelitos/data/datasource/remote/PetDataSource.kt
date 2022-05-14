package co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote

import android.util.Log
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Result
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.PgsService
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.PetDto
import java.io.IOException
import javax.inject.Inject

class PetDataSource @Inject constructor(private val apiService: PgsService) {
    suspend fun getPetsByUser(idUser: Int): Result<List<PetDto>> {
        return try{
            val response = apiService.getPetsByUser(idUser)
            Result.Success(response)
        } catch(e: Throwable){
            Log.e("PetDataSource", "Error retrofit", e)
            Result.Error(IOException("Error logging in", e))
        }
    }
}