package co.edu.udistrital.espingsw.huellitaspelitos.ui.pet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmDeletePetViewModel @Inject constructor(private val petRepository: PetRepository) :
    ViewModel() {

    private val _resultDelete = MutableLiveData<Int>()
    val resultDelete: LiveData<Int>
        get() = _resultDelete

    fun deletePet(idPet: Int) {
        viewModelScope.launch {
            _resultDelete.value = petRepository.deletePet(idPet)
        }

    }
}