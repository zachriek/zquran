package com.zachriek.data.local

import com.zachriek.domain.Profile
import com.zachriek.domain.User
import com.zachriek.domain.repository.EditProfileRepository
import com.zachriek.domain.repository.HomeRepository
import com.zachriek.domain.repository.LoginRepository
import com.zachriek.domain.repository.RegisterRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import javax.inject.Inject

class LocalRepository @Inject constructor (
    private val dataStoreManager: DataStoreManager
): LoginRepository, RegisterRepository, HomeRepository, EditProfileRepository {
    override suspend fun validateInput(username: String, password: String): Boolean {
        return username.isNotEmpty()
                && username.isNotBlank()
                && password.isNotEmpty()
                && password.isNotBlank()
    }

    override suspend fun validateInput(user: User): Boolean {
        return user.name.isNotEmpty()
                && user.name.isNotBlank()
                && user.username.isNotEmpty()
                && user.username.isNotBlank()
                && user.email.isNotEmpty()
                && user.email.isNotBlank()
                && user.phone.isNotEmpty()
                && user.phone.isNotBlank()
                && user.password.isNotEmpty()
                && user.password.isNotBlank()
    }

    override suspend fun setToken(token: String) {
        dataStoreManager.setToken(token)
    }

    override suspend fun getToken(): Flow<String?> {
        return dataStoreManager.getToken()
    }

    override suspend fun clearToken() {
        dataStoreManager.clearToken()
    }

    override suspend fun validateProfileInput(user: Profile): Boolean {
        return user.name.isNotEmpty()
                && user.name.isNotBlank()
                && user.username.isNotEmpty()
                && user.username.isNotBlank()
                && user.email.isNotEmpty()
                && user.email.isNotBlank()
                && user.phone.isNotEmpty()
                && user.phone.isNotBlank()
    }

    override suspend fun validateProfileImage(image: MultipartBody.Part): Boolean {
        return image != null
    }

    override suspend fun loadProfileImage(): Flow<String?> {
        return dataStoreManager.loadProfileImage()
    }

    override suspend fun saveProfileImage(profileImage: String) {
        dataStoreManager.saveProfileImage(profileImage)
    }
}