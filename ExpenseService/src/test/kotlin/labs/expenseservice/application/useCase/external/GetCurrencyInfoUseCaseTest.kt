package labs.expenseservice.application.useCase.external

import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.application.useCase.CurrencyProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.UUID

class GetCurrencyInfoUseCaseTest {

    private val currencyProvider: CurrencyProvider = mock()
    private val useCase = GetCurrencyInfoUseCase(currencyProvider)

    @Test
    fun `should return currency info successfully`() {
        val currencyId = UUID.randomUUID()
        val info = CurrencyInfo(currencyId, "USD")
        whenever(currencyProvider.getCurrencyInfoById(currencyId)).thenReturn(info)

        val result = useCase.execute(currencyId)

        assertThat(result.code).isEqualTo("USD")
    }

    @Test
    fun `should wrap IllegalStateException into EntityNotFoundException`() {
        val currencyId = UUID.randomUUID()
        whenever(currencyProvider.getCurrencyInfoById(currencyId))
            .thenThrow(IllegalStateException("No currency"))

        assertThrows<EntityNotFoundException> {
            useCase.execute(currencyId)
        }
    }
}