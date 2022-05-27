package co.edu.udistrital.espingsw.huellitaspelitos.data.repository

import co.edu.udistrital.espingsw.huellitaspelitos.data.util.ApiCallResult
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.PetDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.PetDto
import javax.inject.Inject

class PetRepository @Inject constructor(private val petDataSource: PetDataSource) {

    var listOfPets: List<PetDto>? = null
        private set

    suspend fun getPetsByUser(idUser: Int): ApiCallResult<List<PetDto>> {
        val result = petDataSource.getPetsByUser(idUser)

        if (result is ApiCallResult.Success){
            setListOfPets(result.data)
        }

        return result
    }

    private fun setListOfPets(listOfPets: List<PetDto>){
        this.listOfPets = listOfPets
    }

    suspend fun getPetById(idPet: Int): PetDto? {
        val result = petDataSource.getPetById(idPet)

        if(result is ApiCallResult.Success){
            return result.data
        }
        return null
    }

    suspend fun createPet(pet: PetDto): PetDto? {
        val result = petDataSource.createPet(pet)

        if(result is ApiCallResult.Success){
            return result.data
        }
        return null
    }

    suspend fun deletePet(petId: Int): Int {
        val result = petDataSource.deletePet(petId)

        return if(result is ApiCallResult.Success){ 1 } else { 0 }
    }
}