package com.zachriek.presentation.usecase.login

import com.zachriek.domain.repository.LoginRepository
import com.zachriek.domain.usecase.login.ValidateInputUseCase
import javax.inject.Inject

class LoginValidateInputUseCase @Inject constructor(
    private val loginRepository: LoginRepository
): ValidateInputUseCase {
    override suspend operator fun invoke(username: String, password: String): Boolean {
        return loginRepository.validateInput(username, password)
    }
}