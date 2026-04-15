package labs.expenseservice.application.useCase.external

import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.application.useCase.UserProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.UUID

class GetUserInfoUseCaseTest {

    private val userProvider: UserProvider = mock()
    private val useCase = GetUserInfoUseCase(userProvider)

    @Test
    fun `should return user info when provider finds user`() {
        val userId = UUID.randomUUID()
        val expectedInfo = UserExternalInfo(userId, "John Doe")
        whenever(userProvider.getUserInfoById(userId)).thenReturn(expectedInfo)

        val result = useCase.execute(userId)

        assertThat(result).isEqualTo(expectedInfo)
        verify(userProvider).getUserInfoById(userId)
    }

    @Test
    fun `should throw EntityNotFoundException when provider throws IllegalStateException`() {
        val userId = UUID.randomUUID()
        val errorMessage = "External user not found"
        whenever(userProvider.getUserInfoById(userId)).thenThrow(IllegalStateException(errorMessage))

        val exception = assertThrows<EntityNotFoundException> {
            useCase.execute(userId)
        }

        assertThat(exception.message).isEqualTo(errorMessage)
    }
}