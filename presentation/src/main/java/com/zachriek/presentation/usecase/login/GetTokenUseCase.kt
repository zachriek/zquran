package com.zachriek.presentation.usecase.login

import com.zachriek.domain.repository.LoginRepository
import com.zachriek.domain.usecase.login.GetTokenUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val loginRepository: LoginRepository
): GetTokenUseCase {
    override suspend operator fun invoke(): Flow<String?> {
        return loginRepository.getToken()
    }
}