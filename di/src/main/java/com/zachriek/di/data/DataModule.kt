package com.zachriek.di.data

import android.content.Context
import androidx.work.WorkManager
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.zachriek.data.local.DataStoreManager
import com.zachriek.data.local.LocalRepository
import com.zachriek.data.remote.RemoteRepository
import com.zachriek.data.remote.service.LampahService
import com.zachriek.data.remote.service.QuranService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideOkhttpClient(
        chuckerInterceptor: ChuckerInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideChuckerInterceptor(context: Context): ChuckerInterceptor {
        return ChuckerInterceptor(context)
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideBaseUrl(): List<String> {
        return listOf<String>(
            "https://api.alquran.cloud/v1/",
            "https://lampah-server.vercel.app/api/v1/",
        )
    }

    @Singleton
    @Provides
    fun provideQuranService(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        baseUrl: List<String>
    ): QuranService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl[0])
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(QuranService::class.java)
    }

    @Singleton
    @Provides
    fun provideLampahService(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        baseUrl: List<String>
    ): LampahService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl[1])
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(LampahService::class.java)
    }

    @Singleton
    @Provides
    fun provideWorkManager(
        context: Context,
    ): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideDataStoreManager(
        context: Context,
    ): DataStoreManager {
        return DataStoreManager(context)
    }

    @Singleton
    @Provides
    fun provideLocalRepository(
        dataStoreManager: DataStoreManager
    ): LocalRepository {
        return LocalRepository(dataStoreManager)
    }

    @Singleton
    @Provides
    fun provideRemoteRepository(
        quranService: QuranService,
        lampahService: LampahService
    ): RemoteRepository {
        return RemoteRepository(quranService, lampahService)
    }
}
