package co.edu.udistrital.espingsw.huellitaspelitos.di

import android.content.Context
import android.content.SharedPreferences
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local.GetPushyTokenDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local.GetUserIdDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local.SetPushyTokenDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.local.SetUserIdDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Singleton
    @Provides
    fun sharedPreferencesProvider(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.SHARED_PREFERENCES_ID, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun getUserIdDataSourceProvider(sharedPreferences: SharedPreferences) =
        GetUserIdDataSource(sharedPreferences)

    @Singleton
    @Provides
    fun setUserIdDataSourceProvider(sharedPreferences: SharedPreferences) =
        SetUserIdDataSource(sharedPreferences)

    @Singleton
    @Provides
    fun getPushyTokenDataSourceProvider(sharedPreferences: SharedPreferences) =
        GetPushyTokenDataSource(sharedPreferences)

    @Singleton
    @Provides
    fun setPushyTokenDataSourceProvider(sharedPreferences: SharedPreferences) =
        SetPushyTokenDataSource(sharedPreferences)
}