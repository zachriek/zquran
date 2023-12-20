package com.zachriek.presentation.usecase.profile

import com.zachriek.domain.repository.MainRepository
import com.zachriek.domain.usecase.profile.UploadProfileImageUseCase
import okhttp3.MultipartBody
import javax.inject.Inject

class UploadProfileImageUseCase @Inject constructor(
    private val mainRepository: MainRepository
): UploadProfileImageUseCase {
    override suspend operator fun invoke(token: String, image: MultipartBody.Part): String {
        return mainRepository.uploadProfileImage(token, image)
    }
}