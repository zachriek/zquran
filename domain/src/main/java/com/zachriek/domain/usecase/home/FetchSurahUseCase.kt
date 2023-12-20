package com.zachriek.domain.usecase.home

import com.zachriek.domain.Surah

interface FetchSurahUseCase {
    suspend fun invoke(): List<Surah>
}