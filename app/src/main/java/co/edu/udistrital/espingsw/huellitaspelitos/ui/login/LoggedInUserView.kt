package co.edu.udistrital.espingsw.huellitaspelitos.ui.login

import android.os.Parcel
import android.os.Parcelable
import java.math.BigDecimal

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView (
    val id: Int?,
    val login: String?,
    val email: String?,
    val phone: BigDecimal?,
    val address: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(BigDecimal::class.java.classLoader) as BigDecimal?,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(login)
        parcel.writeString(email)
        parcel.writeValue(phone)
        parcel.writeString(address)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoggedInUserView> {
        override fun createFromParcel(parcel: Parcel): LoggedInUserView {
            return LoggedInUserView(parcel)
        }

        override fun newArray(size: Int): Array<LoggedInUserView?> {
            return arrayOfNulls(size)
        }
    }

}