package co.edu.udistrital.espingsw.huellitaspelitos.di

import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.*
import co.edu.udistrital.espingsw.huellitaspelitos.data.usecase.GetUserIdUseCase
import co.edu.udistrital.espingsw.huellitaspelitos.data.usecase.SetPushyTokenUseCase
import co.edu.udistrital.espingsw.huellitaspelitos.data.usecase.SetUserIdUseCase
import co.edu.udistrital.espingsw.huellitaspelitos.ui.gallery.GalleryViewModel
import co.edu.udistrital.espingsw.huellitaspelitos.ui.home.HomeViewModel
import co.edu.udistrital.espingsw.huellitaspelitos.ui.login.LoginViewModel
import co.edu.udistrital.espingsw.huellitaspelitos.ui.notifications.NotificationsOptionsViewModel
import co.edu.udistrital.espingsw.huellitaspelitos.ui.pet.AddPetViewModel
import co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.estheticist.viewmodel.ContinueServicesViewModel
import co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.estheticist.viewmodel.EndServicesViewModel
import co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.estheticist.viewmodel.StartServicesViewModel
import co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.estheticist.viewmodel.VisitCreatedByPetOwnerViewModel
import co.edu.udistrital.espingsw.huellitaspelitos.ui.userregistry.UserRegistryViewModel
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
        setUserIdUseCase: SetUserIdUseCase,
        setPushyTokenUseCase: SetPushyTokenUseCase
    ) = LoginViewModel(loginRepository, setUserIdUseCase, setPushyTokenUseCase)

    @Provides
    fun homeViewModelProvider(getUserIdUseCase: GetUserIdUseCase, petRepository: PetRepository) =
        HomeViewModel(getUserIdUseCase, petRepository)

    @Provides
    fun notificationOptionsViewModelProvider(
        loginRepository: LoginRepository,
        visitRepository: VisitRepository
    ) = NotificationsOptionsViewModel(loginRepository, visitRepository)

    @Provides
    fun addPetViewModelProvider(petRepository: PetRepository) = AddPetViewModel(petRepository)

    @Provides
    fun userRegistryViewModelProvider(
        userRepository: UserRepository,
        clientRepository: ClientRepository
    ) = UserRegistryViewModel(userRepository, clientRepository)

    @Provides
    fun galleryViewModelProvider(loginRepository: LoginRepository) =
        GalleryViewModel(loginRepository)

    @Provides
    fun visitCreatedByPetOwnerViewModelProvider(
        visitRepository: VisitRepository,
        petRepository: PetRepository,
        userRepository: UserRepository,
        clientRepository: ClientRepository
    ) = VisitCreatedByPetOwnerViewModel(visitRepository, petRepository, userRepository,
        clientRepository)

    @Provides
    fun startServicesViewModelProvider(visitRepository: VisitRepository) =
        StartServicesViewModel(visitRepository)

    @Provides
    fun continueServicesViewModelProvider(visitRepository: VisitRepository) =
        ContinueServicesViewModel(visitRepository)

    @Provides
    fun endServicesViewModelProvider(visitRepository: VisitRepository) =
        EndServicesViewModel(visitRepository)
}