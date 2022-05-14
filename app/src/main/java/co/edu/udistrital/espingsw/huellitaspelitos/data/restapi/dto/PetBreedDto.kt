package co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PetBreedDto(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("petType")
    var petType: PetTypeDto? = null
) : Serializable
