package com.zachriek.presentation.usecase.login

import com.zachriek.domain.Login
import com.zachriek.domain.LoginBody
import com.zachriek.domain.repository.GuestRepository
import com.zachriek.domain.usecase.login.LoginUseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val guestRepository: GuestRepository
): LoginUseCase {
    override suspend operator fun invoke(user: LoginBody): Login {
        return guestRepository.login(user)
    }
}