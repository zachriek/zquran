package com.zachriek.presentation.usecase.profile

import com.zachriek.domain.Profile
import com.zachriek.domain.repository.MainRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class UpdateProfileUseCaseTest {
    private val repository = mock<MainRepository>()

    private val useCase = UpdateProfileUseCase(repository)

    @Test
    fun `test invoke return success message`() = runTest {
        // Given
        val name = "Muhammad Zachrie Kurniawan"
        val username = "zachriek"
        val email = "zachrie2005@gmail.com"
        val phone = "081274786920"
        val user = Profile(name, username, email, phone, null)
        val token = "token"
        val expected = "Berhasil memperbarui profil!"

        // When
        whenever(repository.updateProfile(token, user)).thenReturn(expected)
        val actual = useCase.invoke(token, user)

        // Then
        Assert.assertEquals(expected, actual)
    }
}