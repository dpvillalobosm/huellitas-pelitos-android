package co.edu.udistrital.espingsw.huellitaspelitos.data.service.dto

import com.google.gson.annotations.SerializedName

data class LoginDTO(
    @SerializedName("username")
    var username: String? = null,
    @SerializedName("password")
    var password: String? = null
)
