package com.zachriek.domain.usecase.profile

interface SaveProfileImageUseCase {
    suspend fun invoke(
        profilePhoto: String
    )
}