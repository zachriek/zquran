package com.zachriek.domain.repository

import com.zachriek.domain.Profile
import com.zachriek.domain.Surah
import com.zachriek.domain.SurahDetail
import okhttp3.MultipartBody

interface MainRepository {
    suspend fun fetchAllSurahData(): List<Surah>
    suspend fun fetchSurahDetail(noSurah: Int): SurahDetail
    suspend fun fetchProfile(token: String): Profile
    suspend fun updateProfile(token: String, user: Profile): String
    suspend fun uploadProfileImage(token: String, image: MultipartBody.Part): String
}