package co.edu.udistrital.espingsw.huellitaspelitos.data.usecase

import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.GetPushyTokenRepository

class GetPushyTokenUseCase constructor(private val repository: GetPushyTokenRepository) {
    operator fun invoke(): String? = repository()
}