package com.zachriek.presentation.usecase.profile

import com.zachriek.domain.Profile
import com.zachriek.domain.repository.EditProfileRepository
import com.zachriek.domain.usecase.profile.ProfileValidateInputUseCase
import javax.inject.Inject

class ProfileValidateInputUseCase @Inject constructor(
    private val editProfileRepository: EditProfileRepository
): ProfileValidateInputUseCase {
    override suspend operator fun invoke(user: Profile): Boolean {
        return editProfileRepository.validateProfileInput(user)
    }
}