package com.zachriek.presentation.usecase.register

import com.zachriek.domain.User
import com.zachriek.domain.repository.RegisterRepository
import com.zachriek.domain.usecase.register.RegisterValidateInputUseCase
import javax.inject.Inject

class RegisterValidateInputUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
): RegisterValidateInputUseCase {
    override suspend operator fun invoke(user: User): Boolean {
        return registerRepository.validateInput(user)
    }
}