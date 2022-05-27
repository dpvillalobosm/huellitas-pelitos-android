package co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote

import android.util.Log
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.PgsService
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.ClientDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.ApiCallResult
import java.io.IOException
import javax.inject.Inject

class ClientDataSource @Inject constructor(private val apiService: PgsService) {
    suspend fun createClient(client: ClientDto): ApiCallResult<ClientDto> {
        return try{
            val response = apiService.createClient(client)
            ApiCallResult.Success(response)
        } catch (e: Throwable) {
            Log.e("ClientDataSource", "Error retrofit", e)
            ApiCallResult.Error(IOException("Error creating client", e))
        }
    }

    suspend fun getClientById(idClient: Int): ApiCallResult<ClientDto> {
        return try{
            val response = apiService.getClientById(idClient)
            ApiCallResult.Success(response)
        } catch (e: Throwable) {
            Log.e("ClientDataSource", "Error retrofit", e)
            ApiCallResult.Error(IOException("Error getting client", e))
        }
    }
}