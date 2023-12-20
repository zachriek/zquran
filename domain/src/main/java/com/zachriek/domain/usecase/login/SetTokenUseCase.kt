package com.zachriek.domain.usecase.login

interface SetTokenUseCase {
    suspend fun invoke(
        token: String
    )
}