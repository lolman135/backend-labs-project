package labs.expenseservice.application.useCase.record

import labs.expenseservice.application.useCase.UseCase
import labs.expenseservice.domain.record.Record
import labs.expenseservice.domain.record.RecordRepository

class GetAllRecordsUseCase(private val recordRepository: RecordRepository) : UseCase<Unit, List<Record>> {

    override fun execute(command: Unit) = recordRepository.findAll()
}