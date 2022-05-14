package co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PetTypeDto(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null
) : Serializable
