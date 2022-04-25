package co.edu.udistrital.espingsw.huellitaspelitos.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udistrital.espingsw.huellitaspelitos.data.LoginDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.LoginRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.service.PgsService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource(
                        apiService = getApiService()
                    )
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    private fun getApiService(): PgsService {
        val lenient = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder().baseUrl("http://192.168.100.64:8080/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(lenient)).build()
        return retrofit.create(PgsService::class.java)
    }
}