package co.edu.udistrital.espingsw.huellitaspelitos.ui.pet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.PetRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.PetDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(private val petRepository: PetRepository) : ViewModel() {
    private val _pets = MutableLiveData<List<PetDto>?>()

    val pets: LiveData<List<PetDto>?>
        get() = _pets

    fun getAllPetsByUser(){
        _pets.value = petRepository.listOfPets
    }
}