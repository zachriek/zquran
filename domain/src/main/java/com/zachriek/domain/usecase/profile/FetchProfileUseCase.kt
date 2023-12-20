package com.zachriek.domain.usecase.profile

import com.zachriek.domain.Profile

interface FetchProfileUseCase {
    suspend fun invoke(token: String): Profile
}