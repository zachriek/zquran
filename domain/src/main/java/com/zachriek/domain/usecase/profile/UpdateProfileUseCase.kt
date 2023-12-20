package com.zachriek.domain.usecase.profile

import com.zachriek.domain.Profile

interface UpdateProfileUseCase {
    suspend fun invoke(token: String, user: Profile): String
}