package com.zachriek.domain.usecase.profile

import okhttp3.MultipartBody

interface ProfileImageValidateUseCase {
    suspend fun invoke(
        image: MultipartBody.Part
    ): Boolean
}