package co.edu.udistrital.espingsw.huellitaspelitos.data.util

enum class NotificationType(val type: Int) {
    START_CLIENT(1),
    START_ESTHETICIST(2),
    CONTINUE_SERVICE(3),
    FINISH_SERVICES(4)
}