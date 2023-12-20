package com.zachriek.presentation.usecase.profile

import com.zachriek.domain.repository.EditProfileRepository
import com.zachriek.domain.usecase.profile.SaveProfileImageUseCase
import javax.inject.Inject

class SaveProfileImageUseCase @Inject constructor(
    private val editProfileRepository: EditProfileRepository
): SaveProfileImageUseCase {
    override suspend operator fun invoke(profilePhoto: String) {
        editProfileRepository.saveProfileImage(profilePhoto)
    }
}