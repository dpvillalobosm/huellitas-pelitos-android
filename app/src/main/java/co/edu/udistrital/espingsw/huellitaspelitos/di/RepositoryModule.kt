package co.edu.udistrital.espingsw.huellitaspelitos.di

import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local.GetUserIdDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local.SetUserIdDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.LoginDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.PetDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.GetUserIdRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.LoginRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.PetRepository
import co.edu.udistrital.espingsw.huellitaspelitos.data.repository.SetUserIdRepository
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
    fun loginRepositoryProvider(loginDataSource: LoginDataSource) = LoginRepository(loginDataSource)

    @Singleton
    @Provides
    fun petRepositoryProvider(petDataSource: PetDataSource) = PetRepository(petDataSource)
}