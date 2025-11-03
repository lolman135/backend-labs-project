package labs.expenseservice.application.useCase.record

import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.application.useCase.CurrencyProvider
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
    private val userProvider: UserProvider,
    private val currencyProvider: CurrencyProvider
) : UseCase<CreateRecordCommand, Record> {

    override fun execute(command: CreateRecordCommand): Record {

        if (!categoryRepository.existsById(command.categoryId))
            throw EntityNotFoundException("Category with id=${command.categoryId} not found")

        if (!userProvider.userExistsById(command.userId))
            throw EntityNotFoundException("User with id=${command.userId} not found")

        if (!currencyProvider.currencyExistsById(command.currencyId))
            throw EntityNotFoundException("Currency with id=${command.userId} not found")

        val record = Record(
            id = UUID.randomUUID(),
            categoryId = command.categoryId,
            userId = command.userId,
            creationTime = LocalDateTime.now(),
            totalCost = command.totalCost,
            currencyId = command.currencyId
        )

        return recordRepository.save(record)
    }
}