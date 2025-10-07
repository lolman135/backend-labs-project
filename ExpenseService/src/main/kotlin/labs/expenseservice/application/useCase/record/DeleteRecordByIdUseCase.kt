package labs.expenseservice.application.useCase.record

import labs.expenseservice.application.useCase.UseCase
import labs.expenseservice.domain.record.RecordRepository
import java.util.UUID

class DeleteRecordByIdUseCase(private val recordRepository: RecordRepository) : UseCase<UUID, Unit> {

    override fun execute(id: UUID) {
        recordRepository.deleteById(id)
    }
}