package co.edu.udistrital.espingsw.huellitaspelitos.ui.userregistry

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.ClientRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.UserRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.ClientDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.UserDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class UserRegistryViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val clientRepository: ClientRepository
) : ViewModel() {

    private val _goToUserCreated = MutableLiveData<Boolean>()
    val goToUserCreated: LiveData<Boolean>
        get() = _goToUserCreated

    @RequiresApi(Build.VERSION_CODES.O)
    fun createUserAndClient(userRegistryDTO: UserRegistryDTO) {
        var resultUserCreation: UserDto?
        var resultClientCreation: ClientDto?

        val userToCreate = UserDto(id = null, email = userRegistryDTO.email,
            password = userRegistryDTO.password, login = userRegistryDTO.login,
            phone = BigDecimal(userRegistryDTO.phone), address = userRegistryDTO.address,
            token = userRegistryDTO.token)

        viewModelScope.launch {
            resultUserCreation = userRepository.createUser(userToCreate)

            if(resultUserCreation != null) {
                val clientToCreate = ClientDto(id = null, clientName = userRegistryDTO.name,
                    clientNumber = userRegistryDTO.documentNumber.toInt(),
                    documentType = userRegistryDTO.documentType, lastVisited = LocalDateTime.now(),
                    discount = 0, pendingForPayment = 0, user = resultUserCreation?.id
                )

                resultClientCreation = clientRepository.createClient(clientToCreate)

                _goToUserCreated.value = resultClientCreation != null

            } else {
                _goToUserCreated.value = false
            }
        }

    }
}