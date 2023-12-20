package com.zachriek.domain.usecase.login

import com.zachriek.domain.Login
import com.zachriek.domain.LoginBody

interface LoginUseCase {
    suspend fun invoke(
        user: LoginBody
    ): Login
}