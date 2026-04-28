package labs.currencyservice.application.usecase

import labs.currencyservice.application.exception.EntityNotFoundException
import labs.currencyservice.domain.Currency
import labs.currencyservice.domain.CurrencyRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.UUID

class CurrencyReadDeleteUseCaseTest {

    private val repository: CurrencyRepository = mock()

    @Test
    fun `GetAllCurrenciesUseCase should return list from repository`() {
        // GIVEN
        val useCase = GetAllCurrenciesUseCase(repository)
        val currencies = listOf(
            Currency(UUID.randomUUID(), "USD", "Dollar", "$"),
            Currency(UUID.randomUUID(), "EUR", "Euro", "€")
        )
        whenever(repository.findAll()).thenReturn(currencies)

        // WHEN
        val result = useCase.execute(Unit)

        // THEN
        assertThat(result).hasSize(2)
        assertThat(result[0].code).isEqualTo("USD")
        verify(repository).findAll()
    }

    @Test
    fun `GetCurrencyByIdUseCase should return currency when it exists`() {
        // GIVEN
        val useCase = GetCurrencyByIdUseCase(repository)
        val id = UUID.randomUUID()
        val currency = Currency(id, "GBP", "Pound", "£")
        whenever(repository.findById(id)).thenReturn(currency)

        // WHEN
        val result = useCase.execute(id)

        // THEN
        assertThat(result).isEqualTo(currency)
        verify(repository).findById(id)
    }

    @Test
    fun `GetCurrencyByIdUseCase should throw exception when not found`() {
        // GIVEN
        val useCase = GetCurrencyByIdUseCase(repository)
        val id = UUID.randomUUID()
        whenever(repository.findById(id)).thenReturn(null)

        // WHEN & THEN
        val exception = assertThrows<EntityNotFoundException> {
            useCase.execute(id)
        }
        assertThat(exception.message).contains("Currency with id=$id not found")
    }

    @Test
    fun `DeleteCurrencyByIdUseCase should call repository delete method`() {
        // GIVEN
        val useCase = DeleteCurrencyByIdUseCase(repository)
        val id = UUID.randomUUID()

        // WHEN
        useCase.execute(id)

        // THEN
        verify(repository).deleteById(id)
    }
}