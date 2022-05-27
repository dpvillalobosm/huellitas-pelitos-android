package co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class UserDto(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("password")
    var password: String? = null,
    @SerializedName("login")
    var login: String? = null,
    @SerializedName("phone")
    var phone: BigDecimal? = null,
    @SerializedName("address")
    var address: String? = null,
    @SerializedName("token")
    var token: String? = null
)
