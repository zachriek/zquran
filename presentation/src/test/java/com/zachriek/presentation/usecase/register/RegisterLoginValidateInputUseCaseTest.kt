package com.zachriek.presentation.usecase.register

import com.zachriek.domain.User
import com.zachriek.domain.repository.RegisterRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RegisterLoginValidateInputUseCaseTest {
    private val repository = mock<RegisterRepository>()

    private val useCase = RegisterValidateInputUseCase(repository)

    @Test
    fun `test invoke return true`() = runTest {
        // Given
        val name = "Muhammad Zachrie Kurniawan"
        val username = "zachriek"
        val email = "zachriek@gmail.com"
        val phone = "081274786920"
        val password = "password"
        val user = User(name, username, email, phone, password)
        val expected = true

        // When
        whenever(repository.validateInput(user)).thenReturn(expected)
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
        val password = ""
        val user = User(name, username, email, phone, password)
        val expected = false

        // When
        whenever(repository.validateInput(user)).thenReturn(expected)
        val actual = useCase.invoke(user)

        // Then
        Assert.assertEquals(expected, actual)
    }
}