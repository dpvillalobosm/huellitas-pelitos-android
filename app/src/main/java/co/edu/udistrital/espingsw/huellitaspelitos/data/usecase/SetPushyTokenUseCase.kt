package co.edu.udistrital.espingsw.huellitaspelitos.data.usecase

import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.SetPushyTokenRepository
import javax.inject.Inject

class SetPushyTokenUseCase @Inject constructor(private val repository: SetPushyTokenRepository) {
    operator fun invoke(pushyTokenToSet: String) = repository(pushyTokenToSet)
}