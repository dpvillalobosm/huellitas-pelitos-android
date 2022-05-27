package co.edu.udistrital.espingsw.huellitaspelitos.data.repository

import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.ClientDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.ClientDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.ApiCallResult
import javax.inject.Inject

class ClientRepository @Inject constructor(private val clientDataSource: ClientDataSource) {



    suspend fun createClient(client: ClientDto): ClientDto? {
        val response = clientDataSource.createClient(client)

        if(response is ApiCallResult.Success){
            return response.data
        }
        return null
    }

    suspend fun getClientById(idClient: Int): ClientDto? {
        val response = clientDataSource.getClientById(idClient)

        if(response is ApiCallResult.Success){
            return response.data
        }
        return null
    }
}