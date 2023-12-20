package com.zachriek.zquran.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import com.zachriek.domain.Profile
import com.zachriek.presentation.usecase.home.ClearTokenUseCase
import com.zachriek.presentation.usecase.login.GetTokenUseCase
import com.zachriek.presentation.usecase.profile.ApplyBlurUseCase
import com.zachriek.presentation.usecase.profile.FetchProfileUseCase
import com.zachriek.presentation.usecase.profile.LoadProfileImageUseCase
import com.zachriek.presentation.usecase.profile.ProfileImageValidateUseCase
import com.zachriek.presentation.usecase.profile.ProfileValidateInputUseCase
import com.zachriek.presentation.usecase.profile.SaveProfileImageUseCase
import com.zachriek.presentation.usecase.profile.UpdateProfileUseCase
import com.zachriek.presentation.usecase.profile.UploadProfileImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val clearTokenUseCase: ClearTokenUseCase,
    private val profileValidateInputUseCase: ProfileValidateInputUseCase,
    private val profileImageValidateUseCase: ProfileImageValidateUseCase,
    private val loadProfileImageUseCase: LoadProfileImageUseCase,
    private val saveProfileImageUseCase: SaveProfileImageUseCase,
    private val fetchProfileUseCase: FetchProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val uploadProfileImageUseCase: UploadProfileImageUseCase,
    private val applyBlurUseCase: ApplyBlurUseCase,
): ViewModel() {
    private val _profileData: MutableLiveData<Profile> = MutableLiveData()
    val profileData: LiveData<Profile> = _profileData

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _dataLoading: MutableLiveData<Boolean> = MutableLiveData()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _logoutLoading: MutableLiveData<Boolean> = MutableLiveData()
    val logoutLoading: LiveData<Boolean> = _logoutLoading

    private val _profileImageLoading: MutableLiveData<Boolean> = MutableLiveData()
    val profileImageLoading: LiveData<Boolean> = _profileImageLoading

    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message

    private val _profileImageMessage: MutableLiveData<String> = MutableLiveData()
    val profileImageMessage: LiveData<String> = _profileImageMessage

    private val _profileImage: MutableLiveData<String> = MutableLiveData()
    val profileImage: LiveData<String> = _profileImage

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun getProfile() {
        _dataLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                fetchProfileUseCase.invoke(getTokenUseCase.invoke().first()!!)
            }.onFailure { exception ->
                withContext(Dispatchers.Main) {
                    _dataLoading.value = false
                    _error.value = exception.message
                }
            }.onSuccess { profile ->
                withContext(Dispatchers.Main) {
                    _dataLoading.value = false
                    _profileData.value = profile
                }
            }
        }
    }

    fun updateProfile(user: Profile) {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            if (profileValidateInputUseCase.invoke(user)) {
                runCatching {
                    updateProfileUseCase.invoke(getTokenUseCase.invoke().first()!!, user)
                }.onFailure { exception ->
                    withContext(Dispatchers.Main) {
                        _loading.value = false
                        _error.value = exception.message
                    }
                }.onSuccess { profile ->
                    withContext(Dispatchers.Main) {
                        _loading.value = false
                        _message.value = profile
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

    fun updateProfileImage(image: MultipartBody.Part) {
        _profileImageLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            if (profileImageValidateUseCase.invoke(image)) {
                runCatching {
                    uploadProfileImageUseCase.invoke(getTokenUseCase.invoke().first()!!, image)
                }.onFailure { exception ->
                    withContext(Dispatchers.Main) {
                        _profileImageLoading.value = false
                        _error.value = exception.message
                    }
                }.onSuccess { profile ->
                    withContext(Dispatchers.Main) {
                        _profileImageLoading.value = false
                        _profileImageMessage.value = profile
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    _error.value = "Field tidak valid!"
                    _profileImageLoading.value = false
                }
            }
        }
    }

    fun applyBlurImage(profileImage: Uri) {
        applyBlurUseCase.invoke(profileImage)
    }

    fun saveBlurImage(profileImage: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveProfileImageUseCase.invoke(profileImage)

            loadProfileImageUseCase.invoke()
                .catch { throwable ->
                    withContext(Dispatchers.Main) {
                        _error.value = throwable.message
                    }
                }
                .collectLatest { profileImage ->
                    withContext(Dispatchers.Main) {
                        _profileImage.value = profileImage
                    }
                }
        }
    }

    fun getOutputWorkerInfo(): LiveData<List<WorkInfo>> {
        return applyBlurUseCase.getWorkManagerLiveData()
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