package co.edu.udistrital.espingsw.huellitaspelitos.data.usecase

import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.GetUserIdRepository
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(private val getUserIdRepository: GetUserIdRepository) {
    operator fun invoke(): Int = getUserIdRepository()
}