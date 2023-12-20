package com.zachriek.presentation.usecase.home

import com.zachriek.domain.Surah
import com.zachriek.domain.repository.MainRepository
import com.zachriek.domain.usecase.home.FetchSurahUseCase
import javax.inject.Inject

class FetchSurahUseCase @Inject constructor(
    private val mainRepository: MainRepository
): FetchSurahUseCase {
    override suspend operator fun invoke(): List<Surah> {
        return mainRepository.fetchAllSurahData()
    }
}