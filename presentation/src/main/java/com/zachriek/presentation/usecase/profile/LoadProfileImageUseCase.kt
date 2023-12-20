package com.zachriek.presentation.usecase.profile

import com.zachriek.domain.repository.EditProfileRepository
import com.zachriek.domain.usecase.profile.LoadProfileImageUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadProfileImageUseCase @Inject constructor(
    private val editProfileRepository: EditProfileRepository
): LoadProfileImageUseCase {
    override suspend operator fun invoke(): Flow<String?> {
        return editProfileRepository.loadProfileImage()
    }
}