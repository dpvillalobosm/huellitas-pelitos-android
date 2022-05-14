package co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class UserDto(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("login")
    var login: String?,
    @SerializedName("phone")
    var phone: BigDecimal?,
    @SerializedName("address")
    var address: String?
)
