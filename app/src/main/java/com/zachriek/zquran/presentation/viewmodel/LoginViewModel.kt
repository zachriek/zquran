package com.zachriek.zquran.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zachriek.domain.LoginBody
import com.zachriek.presentation.usecase.login.GetTokenUseCase
import com.zachriek.presentation.usecase.login.LoginUseCase
import com.zachriek.presentation.usecase.login.SetTokenUseCase
import com.zachriek.presentation.usecase.login.LoginValidateInputUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    private val loginUseCase: LoginUseCase,
    private val loginValidateInputUseCase: LoginValidateInputUseCase,
    private val setTokenUseCase: SetTokenUseCase,
    private val getTokenUseCase: GetTokenUseCase
): ViewModel() {
    private val _login: MutableLiveData<String> = MutableLiveData()
    val login: LiveData<String> = _login

    private val _authentication = MutableLiveData<String>()
    val authentication: LiveData<String> = _authentication

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun login(user: LoginBody) {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            if (loginValidateInputUseCase.invoke(user.username, user.password)) {
                runCatching {
                    loginUseCase.invoke(user)
                }.onFailure { exception ->
                    withContext(Dispatchers.Main) {
                        _loading.value = false
                        _error.value = exception.message
                    }
                }.onSuccess { value ->
                    withContext(Dispatchers.Main) {
                        _loading.value = false
                        _login.value = value.message
                        _authentication.value = value.token
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    _error.value = "Username atau password tidak valid!"
                    _loading.value = false
                }
            }
        }
    }

    fun setToken(token: String) {
        viewModelScope.launch(Dispatchers.Main) {
            setTokenUseCase.invoke(token)
        }
    }

    fun checkAuth() {
        viewModelScope.launch(Dispatchers.Main) {
            _authentication.value = getTokenUseCase.invoke().first() ?: ""
        }
    }
}