package com.zachriek.domain.repository

interface HomeRepository {
    suspend fun clearToken()
}