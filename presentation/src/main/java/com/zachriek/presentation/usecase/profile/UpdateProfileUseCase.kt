package com.zachriek.presentation.usecase.profile

import com.zachriek.domain.Profile
import com.zachriek.domain.repository.MainRepository
import com.zachriek.domain.usecase.profile.UpdateProfileUseCase
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val mainRepository: MainRepository
): UpdateProfileUseCase {
    override suspend operator fun invoke(token: String, user: Profile): String {
        return mainRepository.updateProfile(token, user)
    }
}