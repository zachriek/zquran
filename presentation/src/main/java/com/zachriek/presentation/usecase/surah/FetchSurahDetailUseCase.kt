package com.zachriek.presentation.usecase.surah

import com.zachriek.domain.SurahDetail
import com.zachriek.domain.repository.MainRepository
import com.zachriek.domain.usecase.surah.FetchSurahDetailUseCase
import javax.inject.Inject

class FetchSurahDetailUseCase @Inject constructor(
    private val mainRepository: MainRepository
): FetchSurahDetailUseCase {
    override suspend operator fun invoke(noSurah: Int): SurahDetail {
        return mainRepository.fetchSurahDetail(noSurah)
    }
}