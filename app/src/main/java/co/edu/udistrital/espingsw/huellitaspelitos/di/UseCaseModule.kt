package co.edu.udistrital.espingsw.huellitaspelitos.di

import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.GetPushyTokenRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.GetUserIdRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.SetPushyTokenRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.SetUserIdRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.usecase.GetPushyTokenUseCase
import co.edu.udistrital.espingsw.huellitaspelitos.data.usecase.GetUserIdUseCase
import co.edu.udistrital.espingsw.huellitaspelitos.data.usecase.SetPushyTokenUseCase
import co.edu.udistrital.espingsw.huellitaspelitos.data.usecase.SetUserIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun getUserIdUseCaseProvider(getUserIdRepository: GetUserIdRepository) =
        GetUserIdUseCase(getUserIdRepository)

    @Singleton
    @Provides
    fun setUserIdUseCaseProvider(setUserIdRepository: SetUserIdRepository) =
        SetUserIdUseCase(setUserIdRepository)

    @Singleton
    @Provides
    fun getPushyTokenUseCaseProvider(getPushyTokenRepository: GetPushyTokenRepository) =
        GetPushyTokenUseCase(getPushyTokenRepository)

    @Singleton
    @Provides
    fun setPushyTokenUseCaseProvider(setPushyTokenRepository: SetPushyTokenRepository) =
        SetPushyTokenUseCase(setPushyTokenRepository)
}