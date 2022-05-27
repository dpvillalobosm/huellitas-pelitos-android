package co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalTime

data class ServiceDto(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("averageTime")
    var averageTime: LocalTime? = null,
    @SerializedName("baseAmount")
    var baseAmount: Int? = null
)
