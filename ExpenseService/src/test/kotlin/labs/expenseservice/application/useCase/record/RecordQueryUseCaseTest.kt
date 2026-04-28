package labs.expenseservice.application.useCase.record

import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.domain.record.Record
import labs.expenseservice.domain.record.RecordRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.UUID

class RecordQueryUseCaseTest {

    private val repository: RecordRepository = mock()

    @Test
    fun `GetAllRecordsByIdsUseCase should call repository with correct params`() {
        val useCase = GetAllRecordsByIdsUseCase(repository)
        val categoryId = UUID.randomUUID()
        val userId = UUID.randomUUID()
        val command = GetAllRecordsByIdsCommand(categoryId, userId)

        useCase.execute(command)

        verify(repository).findAllByIds(userId = userId, categoryId = categoryId)
    }

    @Test
    fun `GetRecordByIdUseCase should throw exception if not found`() {
        val useCase = GetRecordByIdUseCase(repository)
        val id = UUID.randomUUID()
        whenever(repository.findById(id)).thenReturn(null)

        assertThrows<EntityNotFoundException> { useCase.execute(id) }
    }

    @Test
    fun `DeleteRecordByIdUseCase should call delete`() {
        val useCase = DeleteRecordByIdUseCase(repository)
        val id = UUID.randomUUID()

        useCase.execute(id)

        verify(repository).deleteById(id)
    }
}