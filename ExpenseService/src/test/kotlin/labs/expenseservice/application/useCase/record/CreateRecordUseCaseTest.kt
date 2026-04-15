package labs.expenseservice.application.useCase.record

import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.application.useCase.CurrencyProvider
import labs.expenseservice.application.useCase.UserProvider
import labs.expenseservice.domain.category.CategoryRepository
import labs.expenseservice.domain.record.RecordRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.UUID

class CreateRecordUseCaseTest {

    private val recordRepository: RecordRepository = mock()
    private val categoryRepository: CategoryRepository = mock()
    private val userProvider: UserProvider = mock()
    private val currencyProvider: CurrencyProvider = mock()

    private val useCase = CreateRecordUseCase(
        recordRepository, categoryRepository, userProvider, currencyProvider
    )

    @Test
    fun `should create record successfully when all checks pass`() {
        val command = CreateRecordCommand(UUID.randomUUID(), UUID.randomUUID(), 500, UUID.randomUUID())

        whenever(categoryRepository.existsById(command.categoryId)).thenReturn(true)
        whenever(userProvider.userExistsById(command.userId)).thenReturn(true)
        whenever(currencyProvider.currencyExistsById(command.currencyId)).thenReturn(true)
        whenever(recordRepository.save(any())).thenAnswer { it.arguments[0] }

        val result = useCase.execute(command)

        assertThat(result.totalCost).isEqualTo(500)
        assertThat(result.categoryId).isEqualTo(command.categoryId)
        verify(recordRepository).save(any())
    }

    @Test
    fun `should throw exception if category does not exist`() {
        val command = CreateRecordCommand(UUID.randomUUID(), UUID.randomUUID(), 100, UUID.randomUUID())
        whenever(categoryRepository.existsById(any())).thenReturn(false)

        assertThrows<EntityNotFoundException> { useCase.execute(command) }
        verify(recordRepository, never()).save(any())
    }

    @Test
    fun `should throw exception if user does not exist`() {
        val command = CreateRecordCommand(UUID.randomUUID(), UUID.randomUUID(), 100, UUID.randomUUID())
        whenever(categoryRepository.existsById(any())).thenReturn(true)
        whenever(userProvider.userExistsById(any())).thenReturn(false)

        assertThrows<EntityNotFoundException> { useCase.execute(command) }
    }
}