package labs.expenseservice.application.useCase.record

import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.application.useCase.UseCase
import labs.expenseservice.application.useCase.UserProvider
import labs.expenseservice.domain.category.CategoryRepository
import labs.expenseservice.domain.record.Record
import labs.expenseservice.domain.record.RecordRepository
import java.time.LocalDateTime
import java.util.UUID

class CreateRecordUseCase(
    private val recordRepository: RecordRepository,
    private val categoryRepository: CategoryRepository,
    private val userProvider: UserProvider
) : UseCase<CreateRecordCommand, Record> {

    override fun execute(command: CreateRecordCommand): Record {
        if (!categoryRepository.existsById(command.categoryId))
            throw EntityNotFoundException("Category with id=${command.categoryId} not found")

        if (!userProvider.userExistsById(command.userId))
            throw EntityNotFoundException("User with id=${command.userId} not found")

        val record = Record(
            id = UUID.randomUUID(),
            categoryId = command.categoryId,
            userId = command.userId,
            creationTime = LocalDateTime.now(),
            totalCost = command.totalCost
        )
        return recordRepository.save(record)
    }
}