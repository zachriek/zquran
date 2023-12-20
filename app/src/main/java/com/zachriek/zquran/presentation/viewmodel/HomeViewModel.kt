package com.zachriek.zquran.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zachriek.domain.Surah
import com.zachriek.presentation.usecase.home.ClearTokenUseCase
import com.zachriek.presentation.usecase.home.FetchSurahUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchSurahUseCase: FetchSurahUseCase,
    private val clearTokenUseCase: ClearTokenUseCase
): ViewModel() {
    private val _surahData: MutableLiveData<List<Surah>> = MutableLiveData()
    val surahData: LiveData<List<Surah>> = _surahData

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _logoutLoading: MutableLiveData<Boolean> = MutableLiveData()
    val logoutLoading: LiveData<Boolean> = _logoutLoading

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun fetchAllSurahData() {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                fetchSurahUseCase.invoke()
            }.onFailure { exception ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _error.value = exception.message
                }
            }.onSuccess { surah ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _surahData.value = surah
                }
            }
        }
    }

    fun logout() {
        _logoutLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            clearTokenUseCase.invoke()
            withContext(Dispatchers.Main) {
                _logoutLoading.value = false
            }
        }
    }
}