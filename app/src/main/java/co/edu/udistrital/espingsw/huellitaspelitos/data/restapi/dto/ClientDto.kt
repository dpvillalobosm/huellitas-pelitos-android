package co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ClientDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("clientNumber")
    val clientNumber: Int?,
    @SerializedName("documentType")
    val documentType: Int?,
    @SerializedName("clientName")
    val clientName: String?,
    @SerializedName("lastVisited")
    val lastVisited: LocalDateTime?,
    @SerializedName("discount")
    val discount: Int?,
    @SerializedName("pendingForPayment")
    val pendingForPayment: Int?,
    @SerializedName("user")
    val user: Int?
)