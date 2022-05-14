package co.edu.udistrital.espingsw.huellitaspelitos.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.PetRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.PetDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.usecase.GetUserIdUseCase
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val petRepository: PetRepository
) :
    ViewModel() {

    private val _userId = MutableLiveData<Int>()
    val userId: LiveData<Int>
        get () = _userId

    private val _pets = MutableLiveData<List<PetDto>>().apply {
        viewModelScope.launch {
            val result = _userId.value?.let { petRepository.getPetsByUser(it) }

            if(result is Result.Success){
                val listOfPetsForUser = result.data
                value = listOfPetsForUser
            }
        }
    }
    val pets: LiveData<List<PetDto>>
        get() = _pets

    fun getUser(): Int{
        return getUserIdUseCase()
    }

    fun getPets(idUser: Int) {
        viewModelScope.launch {
            val result = petRepository.getPetsByUser(idUser)

            if(result is Result.Success){
                val listOfPetsForUser = result.data
                _pets.postValue(listOfPetsForUser)
            }
        }
    }
}