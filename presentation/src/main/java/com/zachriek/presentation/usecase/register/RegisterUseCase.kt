package com.zachriek.presentation.usecase.register

import com.zachriek.domain.User
import com.zachriek.domain.repository.GuestRepository
import com.zachriek.domain.usecase.register.RegisterUseCase
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val guestRepository: GuestRepository
): RegisterUseCase {
    override suspend operator fun invoke(user: User): String {
        return guestRepository.register(user)
    }
}