package com.zachriek.domain.usecase.register

import com.zachriek.domain.User

interface RegisterUseCase {
    suspend fun invoke(
        user: User
    ): String
}