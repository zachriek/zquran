package com.zachriek.domain.usecase.surah

import com.zachriek.domain.SurahDetail

interface FetchSurahDetailUseCase {
    suspend fun invoke(noSurah: Int): SurahDetail
}