package com.zachriek.domain.usecase.profile

import kotlinx.coroutines.flow.Flow

interface LoadProfileImageUseCase {
    suspend fun invoke(): Flow<String?>
}