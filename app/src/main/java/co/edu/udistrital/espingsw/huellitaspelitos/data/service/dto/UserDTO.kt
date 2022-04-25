package co.edu.udistrital.espingsw.huellitaspelitos.data.service.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("login")
    var login: String?,
    @SerializedName("phone")
    var phone: Double?,
    @SerializedName("address")
    var address: String?
)
