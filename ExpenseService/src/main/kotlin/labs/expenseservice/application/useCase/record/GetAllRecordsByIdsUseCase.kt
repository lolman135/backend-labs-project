package labs.expenseservice.application.useCase.record

import labs.expenseservice.application.useCase.UseCase
import labs.expenseservice.domain.record.Record
import labs.expenseservice.domain.record.RecordRepository

class GetAllRecordsByIdsUseCase(
    private val recordRepository: RecordRepository
) : UseCase<GetAllRecordsByIdsCommand, List<Record>>{

    override fun execute(command: GetAllRecordsByIdsCommand) =
        recordRepository.findAllByIds(userId = command.userId, categoryId = command.categoryId)
}