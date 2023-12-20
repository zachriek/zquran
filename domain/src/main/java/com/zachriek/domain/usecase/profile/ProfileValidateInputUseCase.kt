package com.zachriek.domain.usecase.profile

import com.zachriek.domain.Profile

interface ProfileValidateInputUseCase {
    suspend fun invoke(
        user: Profile
    ): Boolean
}