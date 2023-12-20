package com.zachriek.presentation.usecase.profile

import com.zachriek.domain.Profile
import com.zachriek.domain.repository.EditProfileRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ProfileValidateInputUseCaseTest {
    private val repository = mock<EditProfileRepository>()

    private val useCase = ProfileValidateInputUseCase(repository)

    @Test
    fun `test invoke return true`() = runTest {
        // Given
        val name = "Muhammad Zachrie Kurniawan"
        val username = "zachriek"
        val email = "zachrie2005@gmail.com"
        val phone = "081274786920"
        val user = Profile(name, username, email, phone, null)
        val expected = true

        // When
        whenever(repository.validateProfileInput(user)).thenReturn(expected)
        val actual = useCase.invoke(user)

        // Then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test invoke return false`() = runTest {
        // Given
        val name = ""
        val username = ""
        val email = ""
        val phone = ""
        val image = null
        val user = Profile(name, username, email, phone, image)
        val expected = false

        // When
        whenever(repository.validateProfileInput(user)).thenReturn(expected)
        val actual = useCase.invoke(user)

        // Then
        Assert.assertEquals(expected, actual)
    }
}