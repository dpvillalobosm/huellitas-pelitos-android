package co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.Instant
import java.time.LocalDate

data class PetDto(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("birthDate")
    var birthDate: LocalDate? = null,
    @SerializedName("gender")
    var gender: Int? = null,
    @SerializedName("isSterilized")
    var isSterilized: Int? = null,
    @SerializedName("weight")
    var weight: String? = null,
    @SerializedName("photo")
    var photo: String? = null,
    @SerializedName("additionalObservations")
    var additionalObservations: String? = null,
    @SerializedName("lastVisited")
    var lastVisited: Instant? = null,
    @SerializedName("user")
    var user: UserDto? = null,
    @SerializedName("petBreed")
    var petBreed: PetBreedDto? = null,
    @SerializedName("furStatus")
    var furStatus: FurStatusDto? = null
) : Serializable
