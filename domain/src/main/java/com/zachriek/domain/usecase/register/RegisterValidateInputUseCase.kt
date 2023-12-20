package com.zachriek.domain.usecase.register

import com.zachriek.domain.User

interface RegisterValidateInputUseCase {
    suspend fun invoke(
        user: User
    ): Boolean
}