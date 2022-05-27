package co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote

import android.util.Log
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.PgsService
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.VisitDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.ApiCallResult
import java.io.IOException
import javax.inject.Inject

class VisitDataSource @Inject constructor(private val apiService: PgsService) {
    suspend fun createVisit(visit: VisitDto): ApiCallResult<VisitDto> {
        return try {
            val response = apiService.createVisit(visit)
            ApiCallResult.Success(response)
        } catch (e: Throwable) {
            Log.e("VisitDataSource", "Error retrofit", e)
            ApiCallResult.Error(IOException("Error creating visit", e))
        }
    }

    suspend fun getVisit(idVisit: Int): ApiCallResult<VisitDto> {
        return try{
            val response = apiService.getVisit(idVisit)
            ApiCallResult.Success(response)
        } catch(e: Throwable) {
            Log.e("VisitDataSource", "Error retrofit", e)
            ApiCallResult.Error(IOException("Error getting visit", e))
        }
    }

    suspend fun updateVisit(visit: VisitDto): ApiCallResult<VisitDto>{
        return try{
            val response = apiService.updateVisit(visit)
            ApiCallResult.Success(response)
        } catch(e: Throwable) {
            Log.e("VisitDataSource", "Error retrofit", e)
            ApiCallResult.Error(IOException("Error updating visit", e))
        }
    }
}