package com.zachriek.domain

data class SurahDetail(
    var ayahs: List<Ayat>,
    val edition: Edition,
    val englishName: String,
    val englishNameTranslation: String,
    val name: String,
    val number: Int,
    val numberOfAyahs: Int,
    val revelationType: String
)