package co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local

import android.content.SharedPreferences
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import javax.inject.Inject

class GetUserIdDataSource @Inject constructor(private val sharedPref: SharedPreferences) {
    operator fun invoke() : Int = this.sharedPref.getInt(Constants.USER_ID, 0)
}