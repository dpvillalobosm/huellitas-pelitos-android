package co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto

import java.io.Serializable
import java.time.Instant
import java.time.LocalDate

data class PetDto(
    var id: Int? = null,
    var name: String? = null,
    var birthDate: LocalDate? = null,
    var gender: Int? = null,
    var isSterilized: Int? = null,
    var isVaccined: Int? = null,
    var weight: String? = null,
    var photo: String? = null,
    var additionalObservations: String? = null,
    var lastVisited: Instant? = null,
    var idUser: UserDto? = null,
    var idPetBreed: PetBreedDto? = null,
    var idFurStatus: FurStatusDto? = null
) : Serializable
