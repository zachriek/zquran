package com.zachriek.domain.repository

import com.zachriek.domain.Login
import com.zachriek.domain.LoginBody
import com.zachriek.domain.User

interface GuestRepository {
    suspend fun register(user: User): String
    suspend fun login(user: LoginBody): Login
}

