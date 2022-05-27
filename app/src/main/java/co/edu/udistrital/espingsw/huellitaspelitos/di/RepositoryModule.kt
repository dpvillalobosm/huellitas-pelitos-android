package co.edu.udistrital.espingsw.huellitaspelitos.di

import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local.GetPushyTokenDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local.GetUserIdDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local.SetPushyTokenDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local.SetUserIdDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.ClientDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.LoginDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.PetDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.VisitDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun getUserIdRepositoryProvider(getUserIdDataSource: GetUserIdDataSource) =
        GetUserIdRepository(getUserIdDataSource)

    @Singleton
    @Provides
    fun setUserIdRepositoryProvider(setUserIdDataSource: SetUserIdDataSource) =
        SetUserIdRepository(setUserIdDataSource)

    @Singleton
    @Provides
    fun loginRepositoryProvider(loginDataSource: LoginDataSource) =
        LoginRepository(loginDataSource)

    @Singleton
    @Provides
    fun petRepositoryProvider(petDataSource: PetDataSource) =
        PetRepository(petDataSource)

    @Singleton
    @Provides
    fun getPushyTokenRepositoryProvider(getPushyTokenDataSource: GetPushyTokenDataSource) =
        GetPushyTokenRepository(getPushyTokenDataSource)

    @Singleton
    @Provides
    fun setPushyTokenRepositoryProvider(setPushyTokenDataSource: SetPushyTokenDataSource) =
        SetPushyTokenRepository(setPushyTokenDataSource)

    @Singleton
    @Provides
    fun clientRepositoryProvider(clientDataSource: ClientDataSource) =
        ClientRepository(clientDataSource)

    @Singleton
    @Provides
    fun visitRepositoryProvider(visitDataSource: VisitDataSource) =
        VisitRepository(visitDataSource)
}