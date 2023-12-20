package com.zachriek.zquran.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zachriek.domain.User
import com.zachriek.presentation.usecase.register.RegisterUseCase
import com.zachriek.presentation.usecase.register.RegisterValidateInputUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val registerValidateInputUseCase: RegisterValidateInputUseCase
): ViewModel() {
    private val _register: MutableLiveData<String> = MutableLiveData()
    val register: LiveData<String> = _register

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun register(user: User) {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            if (registerValidateInputUseCase.invoke(user)) {
                runCatching {
                    registerUseCase.invoke(user)
                }.onFailure { exception ->
                    withContext(Dispatchers.Main) {
                        _loading.value = false
                        _error.value = exception.message
                    }
                }.onSuccess { value ->
                    withContext(Dispatchers.Main) {
                        _loading.value = false
                        _register.value = value
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    _error.value = "Field tidak valid!"
                    _loading.value = false
                }
            }
        }
    }
}