package co.edu.udistrital.espingsw.huellitaspelitos.data.repository

import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local.SetPushyTokenDataSource
import javax.inject.Inject

class SetPushyTokenRepository @Inject constructor(private val dataSource: SetPushyTokenDataSource) {
    operator fun invoke(pushyTokenToSet: String): String = dataSource(pushyTokenToSet)
}