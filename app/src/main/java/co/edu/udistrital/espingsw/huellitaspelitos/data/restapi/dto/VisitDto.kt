package co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto

import com.google.gson.annotations.SerializedName
import java.time.Instant
import java.time.LocalTime

data class VisitDto(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("visitDate")
    var visitDate: Instant? = null,
    @SerializedName("initialDescription")
    var initialDescription: String? = null,
    @SerializedName("finalDescription")
    var finalDescription: String? = null,
    @SerializedName("realTimeUsed")
    var realTimeUsed: LocalTime? = null,
    @SerializedName("totalAmount")
    var totalAmount: Int? = null,
    @SerializedName("uuid")
    var uuid: String? = null,
    @SerializedName("photo")
    var photo: String? = null,
    @SerializedName("idService")
    var idService: ServiceDto? = null,
    @SerializedName("idStatus")
    var idStatus: StatusDto? = null,
    @SerializedName("idUser")
    var idUser: UserDto? = null,
    @SerializedName("idPet")
    var idPet: PetDto? = null
)
