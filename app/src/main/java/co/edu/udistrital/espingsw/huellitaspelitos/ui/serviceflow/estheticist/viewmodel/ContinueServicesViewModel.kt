package co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.estheticist.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.VisitRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.VisitDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContinueServicesViewModel @Inject constructor(private val visitRepository: VisitRepository): ViewModel() {

    private var _visitUpdated = MutableLiveData<VisitDto>()
    val visitUpdated: LiveData<VisitDto>
        get() = _visitUpdated

    fun getVisitFromCache(): VisitDto? {
        return visitRepository.visitDto
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateVisit(observations: String, photo: String, visit: VisitDto){
        visit.initialDescription = observations
        visit.photo = photo
        visit.idStatus?.id = 3
        visit.realTimeUsed = visit.realTimeUsed?.plusMinutes(10)

        viewModelScope.launch {
            _visitUpdated.value = visitRepository.updateVisit(visit)
        }
    }

}