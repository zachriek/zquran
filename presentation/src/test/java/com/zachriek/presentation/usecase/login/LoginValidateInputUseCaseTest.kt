package com.zachriek.presentation.usecase.login

import com.zachriek.domain.repository.LoginRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class LoginValidateInputUseCaseTest {
    private val repository = mock<LoginRepository>()

    private val useCase = LoginValidateInputUseCase(repository)

    @Test
    fun `test invoke return true`() = runTest {
        // Given
        val username = "zachriek"
        val password = "password"
        val expected = true

        // When
        whenever(repository.validateInput(username, password)).thenReturn(expected)
        val actual = useCase.invoke(username, password)

        // Then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test invoke return false`() = runTest {
        // Given
        val username = ""
        val password = ""
        val expected = false

        // When
        whenever(repository.validateInput(username, password)).thenReturn(expected)
        val actual = useCase.invoke(username, password)

        // Then
        Assert.assertEquals(expected, actual)
    }
}