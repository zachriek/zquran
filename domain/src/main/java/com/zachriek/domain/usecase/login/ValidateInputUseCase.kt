package com.zachriek.domain.usecase.login

interface ValidateInputUseCase {
    suspend fun invoke(
        username: String,
        password: String,
    ): Boolean
}