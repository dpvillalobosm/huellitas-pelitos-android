package co.edu.udistrital.espingsw.huellitaspelitos.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local.GetUserIdDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.LoginRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.UserDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    fun getLoggedInUser(): UserDto? {
        return loginRepository.user
    }
}