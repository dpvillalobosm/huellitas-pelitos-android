package co.edu.udistrital.espingsw.huellitaspelitos.ui.pet

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.FurStatusDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.PetBreedDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.PetRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.PetDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.UserDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddPetViewModel @Inject constructor(private val petRepository: PetRepository) : ViewModel() {

    private val _petCreated = MutableLiveData<PetDto>()
    val petCreated: LiveData<PetDto>
        get() = _petCreated


    @RequiresApi(Build.VERSION_CODES.O)
    fun createPet(petName: String, petBirthDate: Date, petGender: Boolean, petWeight: String,
                  isPetSterilized: Boolean, isPetVaccined: Boolean, petObservations: String,
                  photo: String, userId: Int){

        val birthDate = petBirthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        val pet = PetDto(name = petName, birthDate = birthDate,
            additionalObservations = petObservations, idFurStatus = FurStatusDto(id = 1),
            idPetBreed = PetBreedDto(id = 1), gender = petGender.toInt(), weight = petWeight,
            isSterilized = isPetSterilized.toInt(), isVaccined = isPetVaccined.toInt(),
            photo = photo, idUser = UserDto(id = userId), lastVisited = Instant.now()
        )

        viewModelScope.launch {
            _petCreated.value = petRepository.createPet(pet)
        }
    }

    private fun Boolean.toInt() = if (this) 1 else 0
}