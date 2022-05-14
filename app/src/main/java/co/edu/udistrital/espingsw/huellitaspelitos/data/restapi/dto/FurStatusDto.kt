package co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FurStatusDto(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("description")
    var description: String? = null
) : Serializable
