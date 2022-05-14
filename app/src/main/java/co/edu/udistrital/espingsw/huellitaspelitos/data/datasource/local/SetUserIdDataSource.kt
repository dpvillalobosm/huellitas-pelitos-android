package co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local

import android.content.SharedPreferences
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import javax.inject.Inject

class SetUserIdDataSource @Inject constructor(private val sharedPref: SharedPreferences) {
    operator fun invoke(userIdToSet: Int) : Int {
        sharedPref.edit().putInt(Constants.USER_ID, userIdToSet).apply()
        return userIdToSet
    }
}