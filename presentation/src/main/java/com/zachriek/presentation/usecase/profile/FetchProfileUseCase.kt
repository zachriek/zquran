package com.zachriek.presentation.usecase.profile

import com.zachriek.domain.Profile
import com.zachriek.domain.repository.MainRepository
import com.zachriek.domain.usecase.profile.FetchProfileUseCase
import javax.inject.Inject

class FetchProfileUseCase @Inject constructor(
    private val mainRepository: MainRepository
): FetchProfileUseCase {
    override suspend operator fun invoke(token: String): Profile {
        return mainRepository.fetchProfile(token)
    }
}