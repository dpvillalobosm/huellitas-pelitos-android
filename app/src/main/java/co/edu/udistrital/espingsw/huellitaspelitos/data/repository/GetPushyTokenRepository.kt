package co.edu.udistrital.espingsw.huellitaspelitos.data.repository

import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local.GetPushyTokenDataSource
import javax.inject.Inject

class GetPushyTokenRepository @Inject constructor(private val dataSource: GetPushyTokenDataSource) {
    operator fun invoke(): String? = dataSource()
}