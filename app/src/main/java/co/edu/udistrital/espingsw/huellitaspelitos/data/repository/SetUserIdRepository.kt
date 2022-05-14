package co.edu.udistrital.espingsw.huellitaspelitos.data.repository

import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local.SetUserIdDataSource
import javax.inject.Inject

class SetUserIdRepository @Inject constructor(private val dataSource: SetUserIdDataSource) {
    operator fun invoke(userIdToSet: Int): Int = dataSource(userIdToSet)
}