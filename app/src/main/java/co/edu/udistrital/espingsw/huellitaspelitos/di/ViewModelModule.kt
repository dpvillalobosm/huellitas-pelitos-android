package co.edu.udistrital.espingsw.huellitaspelitos.di

import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.LoginRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.PetRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.usecase.GetUserIdUseCase
import co.edu.udistrital.espingsw.huellitaspelitos.data.usecase.SetUserIdUseCase
import co.edu.udistrital.espingsw.huellitaspelitos.ui.home.HomeViewModel
import co.edu.udistrital.espingsw.huellitaspelitos.ui.login.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun loginViewModelProvider(
        loginRepository: LoginRepository,
        setUserIdUseCase: SetUserIdUseCase
    ) = LoginViewModel(loginRepository, setUserIdUseCase)

    @Provides
    fun homeViewModelProvider(getUserIdUseCase: GetUserIdUseCase, petRepository: PetRepository) =
        HomeViewModel(getUserIdUseCase, petRepository)
}