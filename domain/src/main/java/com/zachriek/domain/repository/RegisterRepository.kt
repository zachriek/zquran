package com.zachriek.domain.repository

import com.zachriek.domain.User


interface RegisterRepository {
    suspend fun validateInput(user: User): Boolean
}