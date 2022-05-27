package co.edu.udistrital.espingsw.huellitaspelitos.ui.notifications

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.LoginRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.VisitRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalTime
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NotificationsOptionsViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val visitRepository: VisitRepository
) :
    ViewModel() {

    var uuid: String? = ""

    private val _goToVisitCreated = MutableLiveData<Boolean>()
    val goToVisitCreated: LiveData<Boolean>
        get() = _goToVisitCreated

    fun getLoggedInUser(): UserDto? {
        return loginRepository.user
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createVisit(serviceId: Int, userId: Int, petId: Int) {
        var resultVisitCreation: VisitDto?
        uuid = UUID.randomUUID().toString()

        val visit = VisitDto(id = null, visitDate = Instant.now(),
            initialDescription = "Visita creada por el dueño",
            finalDescription = "Pendiente de revisión de esteticista",
            realTimeUsed = LocalTime.of(0, 5), totalAmount = 0,
            uuid = uuid, idService = ServiceDto(id = serviceId),
            idStatus = StatusDto(id = 1), idUser = UserDto(id = 2), idPet = PetDto(id = petId)
        )

        viewModelScope.launch {
            resultVisitCreation = visitRepository.createVisit(visit)

            _goToVisitCreated.value = resultVisitCreation != null
        }

    }
}