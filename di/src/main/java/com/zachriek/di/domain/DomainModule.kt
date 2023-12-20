package com.zachriek.di.domain

import com.zachriek.data.local.LocalRepository
import com.zachriek.data.remote.RemoteRepository
import com.zachriek.domain.repository.EditProfileRepository
import com.zachriek.domain.repository.GuestRepository
import com.zachriek.domain.repository.HomeRepository
import com.zachriek.domain.repository.LoginRepository
import com.zachriek.domain.repository.MainRepository
import com.zachriek.domain.repository.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        remoteRepository: RemoteRepository
    ): MainRepository {
        return remoteRepository
    }

    @Singleton
    @Provides
    fun provideGuestRepository(
        remoteRepository: RemoteRepository
    ): GuestRepository {
        return remoteRepository
    }

    @Singleton
    @Provides
    fun provideLoginRepository(
        localRepository: LocalRepository
    ): LoginRepository {
        return localRepository
    }

    @Singleton
    @Provides
    fun provideRegisterRepository(
        localRepository: LocalRepository
    ): RegisterRepository {
        return localRepository
    }

    @Singleton
    @Provides
    fun provideHomeRepository(
        localRepository: LocalRepository
    ): HomeRepository {
        return localRepository
    }

    @Singleton
    @Provides
    fun provideEditProfileRepository(
        localRepository: LocalRepository
    ): EditProfileRepository {
        return localRepository
    }
}