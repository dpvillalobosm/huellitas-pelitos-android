package co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto

import com.google.gson.annotations.SerializedName

data class LoginDto(
    @SerializedName("username")
    var username: String? = null,
    @SerializedName("password")
    var password: String? = null
)
