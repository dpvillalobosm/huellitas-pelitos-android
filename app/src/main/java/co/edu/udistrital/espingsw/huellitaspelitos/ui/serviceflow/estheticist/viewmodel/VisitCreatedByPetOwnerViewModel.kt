package co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.estheticist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.ClientRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.PetRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.UserRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.VisitRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.ClientDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.PetDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.UserDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.VisitDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VisitCreatedByPetOwnerViewModel @Inject constructor(
    private val visitRepository: VisitRepository,
    private val petRepository: PetRepository,
    private val userRepository: UserRepository,
    private val clientRepository: ClientRepository
) :
    ViewModel() {

    private var _dataToUI = MutableLiveData<ArrayList<String>>()
    val dataToUI: LiveData<ArrayList<String>>
        get() = _dataToUI

    var toReturn: ArrayList<String>? = null
    var visit: VisitDto? = null
    var pet: PetDto? = null
    var user: UserDto? = null
    var client: ClientDto? = null

    fun getDataToShow(idVisit: Int) {
        viewModelScope.launch {
            visit = visitRepository.getVisit(idVisit)
            pet = visit?.idPet?.id?.let { petRepository.getPetById(it) }
            user = pet?.idUser?.id?.let { userRepository.getUserById(it) }
            client = user?.id?.let { clientRepository.getClientById(it) }

            val petName = pet?.name
            val ownerName = client?.clientName
            val uuid = visit?.uuid

            toReturn = arrayListOf()

            if (petName != null) {
                toReturn!!.add(petName)
            }

            if (ownerName != null) {
                toReturn!!.add(ownerName)
            }

            if (uuid != null) {
                toReturn!!.add(uuid)
            }

            _dataToUI.value = toReturn
        }
    }
}