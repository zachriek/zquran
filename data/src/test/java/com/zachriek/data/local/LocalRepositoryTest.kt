package com.zachriek.data.local

import com.zachriek.domain.Profile
import com.zachriek.domain.User
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock

class LocalRepositoryTest {
    private val dataStoreManager = mock<DataStoreManager>()

    private val repository = LocalRepository(dataStoreManager)

    @Test
    fun `test validateInput (login) return true`() =
        runTest {
            // Given
            val username = "zachriek"
            val password = "zachriek"
            val expected = true

            // when
            val actual = repository.validateInput(username, password)

            // Then
            Assert.assertEquals(expected, actual)
        }

    @Test
    fun `test validateInput (login) return false`() =
        runTest {
            // Given
            val username = ""
            val password = ""
            val expected = false

            // when
            val actual = repository.validateInput(username, password)

            // Then
            Assert.assertEquals(expected, actual)
        }

    @Test
    fun `test validateInput (register) return true`() =
        runTest {
            // Given
            val name = "zachriek"
            val username = "zachriek"
            val email = "zachrie2005@gmail.com"
            val phone = "081274786920"
            val password = "zachriek"
            val user = User(name, username, email, phone, password)
            val expected = true

            // when
            val actual = repository.validateInput(user)

            // Then
            Assert.assertEquals(expected, actual)
        }

    @Test
    fun `test validateInput (register) return false`() =
        runTest {
            // Given
            val name = ""
            val username = ""
            val email = ""
            val phone = ""
            val password = ""
            val user = User(name, username, email, phone, password)
            val expected = false

            // when
            val actual = repository.validateInput(user)

            // Then
            Assert.assertEquals(expected, actual)
        }

    @Test
    fun `test validateProfileInput return true`() =
        runTest {
            // Given
            val name = "zachriek"
            val username = "zachriek"
            val email = "zachrie2005@gmail.com"
            val phone = "081274786920"
            val password = "zachriek"
            val user = Profile(name, username, email, phone, password)
            val expected = true

            // when
            val actual = repository.validateProfileInput(user)

            // Then
            Assert.assertEquals(expected, actual)
        }

    @Test
    fun `test validateProfileInput return false`() =
        runTest {
            // Given
            val name = ""
            val username = ""
            val email = ""
            val phone = ""
            val password = ""
            val user = Profile(name, username, email, phone, password)
            val expected = false

            // when
            val actual = repository.validateProfileInput(user)

            // Then
            Assert.assertEquals(expected, actual)
        }
}