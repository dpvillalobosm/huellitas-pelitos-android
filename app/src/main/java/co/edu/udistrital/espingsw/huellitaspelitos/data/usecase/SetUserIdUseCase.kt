package co.edu.udistrital.espingsw.huellitaspelitos.data.usecase

import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.SetUserIdRepository
import javax.inject.Inject

class SetUserIdUseCase @Inject constructor(private val setUserIdRepository: SetUserIdRepository) {
    operator fun invoke(userIdToSet: Int) = setUserIdRepository(userIdToSet)
}