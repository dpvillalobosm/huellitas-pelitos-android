package co.edu.udistrital.espingsw.huellitaspelitos.data.repository

import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local.GetUserIdDataSource
import javax.inject.Inject

class GetUserIdRepository @Inject constructor(private val dataSource: GetUserIdDataSource) {
    operator fun invoke(): Int = dataSource()
}