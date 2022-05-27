package co.edu.udistrital.espingsw.huellitaspelitos.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local.GetUserIdDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.SetUserIdRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserIdDataSource: GetUserIdDataSource,
    private val setUserIdRepository: SetUserIdRepository
) : ViewModel() {

    fun setUserId(userId: Int) {
        setUserIdRepository(userId)
    }

    fun getUserId(): Int {
        return getUserIdDataSource()
    }
}