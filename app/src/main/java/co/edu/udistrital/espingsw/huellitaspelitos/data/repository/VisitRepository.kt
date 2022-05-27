package co.edu.udistrital.espingsw.huellitaspelitos.data.repository

import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.VisitDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.VisitDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.ApiCallResult
import javax.inject.Inject

class VisitRepository @Inject constructor(private val visitDataSource: VisitDataSource){

    var visitDto: VisitDto? = null
    private set

    init {
        visitDto = null
    }

    suspend fun createVisit(visit: VisitDto): VisitDto? {
        val response = visitDataSource.createVisit(visit)

        if(response is ApiCallResult.Success){
            setVisitCache(response.data)
            return response.data
        }
        return null
    }

    suspend fun getVisit(idVisit: Int): VisitDto? {
        val response = visitDataSource.getVisit(idVisit)

        if(response is ApiCallResult.Success){
            setVisitCache(response.data)
            return response.data
        }
        return null
    }

    suspend fun updateVisit(visit: VisitDto): VisitDto? {
        val response = visitDataSource.updateVisit(visit)

        if(response is ApiCallResult.Success){
            setVisitCache(response.data)
            return response.data
        }
        return null
    }

    private fun setVisitCache(visit: VisitDto){
        this.visitDto = visit
    }
}