package co.edu.udistrital.espingsw.huellitaspelitos.ui.userregistry

data class UserRegistryDTO(
    val name: String,
    val documentNumber: String,
    val documentType: Int,
    val login: String,
    val password: String,
    val email: String,
    val phone: String,
    val address: String,
    val token: String
)
