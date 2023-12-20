package com.zachriek.presentation.usecase.login

import com.zachriek.domain.repository.LoginRepository
import com.zachriek.domain.usecase.login.SetTokenUseCase
import javax.inject.Inject

class SetTokenUseCase @Inject constructor(
    private val loginRepository: LoginRepository
): SetTokenUseCase {
    override suspend operator fun invoke(token: String) {
        loginRepository.setToken(token)
    }
}