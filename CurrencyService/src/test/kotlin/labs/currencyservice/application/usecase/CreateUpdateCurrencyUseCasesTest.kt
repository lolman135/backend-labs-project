package labs.currencyservice.application.usecase

import labs.currencyservice.application.exception.EntityAlreadyExistsException
import labs.currencyservice.application.exception.EntityNotFoundException
import labs.currencyservice.domain.Currency
import labs.currencyservice.domain.CurrencyRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.UUID

class CreateUpdateCurrencyUseCasesTest {

    private val repository: CurrencyRepository = mock()
    private val createUseCase = CreateCurrencyUseCase(repository)
    private val updateUseCase = UpdateCurrencyByIdUseCase(repository)

    @Test
    fun `CreateCurrencyUseCase should save new currency`() {
        val command = UpsertCurrencyCommand("EUR", "Euro", "€")
        whenever(repository.existsByCode("EUR")).thenReturn(false)
        whenever(repository.save(any())).thenAnswer { it.arguments[0] }

        val result = createUseCase.execute(command)

        assertThat(result.code).isEqualTo("EUR")
        verify(repository).save(any())
    }

    @Test
    fun `UpdateCurrencyByIdUseCase should throw exception if not found`() {
        val id = UUID.randomUUID()
        whenever(repository.findById(id)).thenReturn(null)

        assertThrows<EntityNotFoundException> {
            updateUseCase.execute(id to UpsertCurrencyCommand("USD", "Dollar", "$"))
        }
    }

    @Test
    fun `UpdateCurrencyByIdUseCase should update successfully`() {
        val id = UUID.randomUUID()
        val existing = Currency(id, "OLD", "Old Name", "O")
        val command = UpsertCurrencyCommand("NEW", "New Name", "N")

        whenever(repository.findById(id)).thenReturn(existing)
        whenever(repository.existsByCode(any())).thenReturn(false)
        whenever(repository.save(any())).thenAnswer { it.arguments[0] }

        val result = updateUseCase.execute(id to command)

        assertThat(result.code).isEqualTo("NEW")
        assertThat(result.name).isEqualTo("New Name")
    }
}