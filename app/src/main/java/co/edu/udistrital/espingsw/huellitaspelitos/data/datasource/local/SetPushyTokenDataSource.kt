package co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local

import android.content.SharedPreferences
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import javax.inject.Inject

class SetPushyTokenDataSource @Inject constructor(private val sharedPref: SharedPreferences) {
    operator fun invoke(tokenToSet: String): String {
        sharedPref.edit().putString(Constants.PUSHY_TOKEN, tokenToSet).apply()
        return tokenToSet
    }
}