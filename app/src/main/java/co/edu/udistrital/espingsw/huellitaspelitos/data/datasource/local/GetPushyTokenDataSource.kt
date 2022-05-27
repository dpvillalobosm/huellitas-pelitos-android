package co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local

import android.content.SharedPreferences
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import javax.inject.Inject

class GetPushyTokenDataSource @Inject constructor(private val sharedPref: SharedPreferences) {
    operator fun invoke() = this.sharedPref.getString(Constants.PUSHY_TOKEN, "")
}