package com.zachriek.presentation.usecase.home

import com.zachriek.domain.repository.HomeRepository
import com.zachriek.domain.usecase.home.ClearTokenUseCase
import javax.inject.Inject

class ClearTokenUseCase @Inject constructor(
    private val homeRepository: HomeRepository
): ClearTokenUseCase {
    override suspend operator fun invoke() {
        homeRepository.clearToken()
    }
}