package co.edu.udistrital.espingsw.huellitaspelitos.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.LoginRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.ApiCallResult

import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.data.usecase.SetPushyTokenUseCase
import co.edu.udistrital.espingsw.huellitaspelitos.data.usecase.SetUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val setUserIdUseCase: SetUserIdUseCase,
    private val setPushyTokenUseCase: SetPushyTokenUseCase
) :
    ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _pushyToken = MutableLiveData<String>()
    val pushyToken: LiveData<String> = _pushyToken

    fun setPushyToken(token: String) {
        _pushyToken.postValue(token)
        setPushyTokenUseCase(token)
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val result = loginRepository.login(username, password)

            if (result is ApiCallResult.Success) {
                val user = result.data

                user.id?.let { setUserIdUseCase(it) }

                _loginResult.value =
                    LoginResult(
                        success = LoggedInUserView(
                            user.id, user.login, user.email,
                            user.phone, user.address
                        )
                    )
            } else {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        }

    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}