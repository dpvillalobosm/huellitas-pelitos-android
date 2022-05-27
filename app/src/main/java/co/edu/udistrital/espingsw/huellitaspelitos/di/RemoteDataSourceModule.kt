package co.edu.udistrital.espingsw.huellitaspelitos.di

import android.os.Build
import androidx.annotation.RequiresApi
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.ClientDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.LoginDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.PetDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.datasource.remote.VisitDataSource
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.PgsService
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.*
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Singleton
    @Provides
    fun okHttpClientProvider(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return httpClient.build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun retrofitProvider(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL_LOCAL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().registerTypeAdapter(
                    LocalDate::class.java,
                    LocalDateTypeAdapter().nullSafe()
                ).registerTypeAdapter(
                    Instant::class.java,
                    InstantTypeAdapter().nullSafe()
                ).registerTypeAdapter(
                    LocalTime::class.java,
                    LocalTimeTypeAdapter().nullSafe()
                ).registerTypeAdapter(
                    LocalDateTime::class.java,
                    LocalDateTimeTypeAdapter().nullSafe()
                ).setLenient().create()
            )
        ).client(okHttpClient).build()

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

    @Singleton
    @Provides
    fun clientDataSourceProvider(pgsService: PgsService) = ClientDataSource(pgsService)

    @Singleton
    @Provides
    fun visitDataSourceProvider(pgsService: PgsService) = VisitDataSource(pgsService)
}