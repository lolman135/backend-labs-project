package labs.expenseservice.application.useCase.record

import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.application.useCase.UseCase
import labs.expenseservice.domain.record.Record
import labs.expenseservice.domain.record.RecordRepository
import java.util.UUID

class GetRecordByIdUseCase(private val recordRepository: RecordRepository) : UseCase<UUID, Record> {

    override fun execute(id: UUID) = recordRepository.findById(id)
        ?: throw EntityNotFoundException("Record with id=$id not found")
}