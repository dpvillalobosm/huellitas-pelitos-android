package co.edu.udistrital.espingsw.huellitaspelitos.data.repository

import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Result
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.PetDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.PetDto
import javax.inject.Inject

class PetRepository @Inject constructor(private val petDataSource: PetDataSource) {

    var listOfPets: List<PetDto>? = null
        private set

    suspend fun getPetsByUser(idUser: Int): Result<List<PetDto>> {
        val result = petDataSource.getPetsByUser(idUser)

        if (result is Result.Success){
            setListOfPets(result.data)
        }

        return result
    }

    private fun setListOfPets(listOfPets: List<PetDto>){
        this.listOfPets = listOfPets
    }


}