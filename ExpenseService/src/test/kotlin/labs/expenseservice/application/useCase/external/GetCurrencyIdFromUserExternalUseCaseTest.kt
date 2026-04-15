package labs.expenseservice.application.useCase.external

import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.application.useCase.UserProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.UUID

class GetCurrencyIdFromUserExternalUseCaseTest {

    private val userProvider: UserProvider = mock()
    private val useCase = GetCurrencyIdFromUserExternalUseCase(userProvider)

    @Test
    fun `should return currency id for user`() {
        val userId = UUID.randomUUID()
        val currencyId = UUID.randomUUID()
        whenever(userProvider.getCurrencyIdByUserId(userId)).thenReturn(currencyId)

        val result = useCase.execute(userId)

        assertThat(result).isEqualTo(currencyId)
    }

    @Test
    fun `should catch IllegalStateException and throw EntityNotFoundException`() {
        val userId = UUID.randomUUID()
        whenever(userProvider.getCurrencyIdByUserId(userId))
            .thenThrow(IllegalStateException("User currency not found"))

        assertThrows<EntityNotFoundException> {
            useCase.execute(userId)
        }
    }
}