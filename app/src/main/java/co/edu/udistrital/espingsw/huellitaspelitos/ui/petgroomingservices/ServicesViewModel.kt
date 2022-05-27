package co.edu.udistrital.espingsw.huellitaspelitos.ui.petgroomingservices

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.PetGroomingRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.ServiceDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServicesViewModel @Inject constructor(private val petGroomingRepository: PetGroomingRepository): ViewModel() {

    private val _services = MutableLiveData<List<ServiceDto>?>()

    val services: LiveData<List<ServiceDto>?>
        get() = _services

    fun getAllServices(){
        viewModelScope.launch {
            val result = petGroomingRepository.getAllServices()
            _services.value = result
        }
    }
}