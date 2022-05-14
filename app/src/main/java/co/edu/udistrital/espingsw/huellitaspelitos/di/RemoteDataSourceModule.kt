package co.edu.udistrital.espingsw.huellitaspelitos.di

import android.os.Build
import androidx.annotation.RequiresApi
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.LoginDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.PetDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.PgsService
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.InstantTypeAdapter
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.LocalDateTypeAdapter
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Instant
import java.time.LocalDate
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun retrofitProvider(): Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL_AWS)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().registerTypeAdapter(
                    LocalDate::class.java,
                    LocalDateTypeAdapter().nullSafe()
                ).registerTypeAdapter(Instant::class.java, InstantTypeAdapter().nullSafe())
                    .setLenient().create()
            )
        )
        .build()

    @Singleton
    @Provides
    fun serviceRestApiProvider(retrofit: Retrofit): PgsService =
        retrofit.create(PgsService::class.java)

    @Singleton
    @Provides
    fun loginDataSourceProvider(pgsService: PgsService) = LoginDataSource(pgsService)

    @Singleton
    @Provides
    fun petDataSourceProvider(pgsService: PgsService) = PetDataSource(pgsService)
}