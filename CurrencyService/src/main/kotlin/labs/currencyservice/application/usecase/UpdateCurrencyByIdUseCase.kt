package labs.currencyservice.application.usecase

import labs.currencyservice.application.exception.EntityAlreadyExistsException
import labs.currencyservice.application.exception.EntityNotFoundException
import labs.currencyservice.domain.Currency
import labs.currencyservice.domain.CurrencyRepository
import java.util.UUID

class UpdateCurrencyByIdUseCase(
    private val currencyRepository: CurrencyRepository
) : UseCase<Pair<UUID, UpsertCurrencyCommand>, Currency>{

    override fun execute(input: Pair<UUID, UpsertCurrencyCommand>): Currency {
        val(id, executableCommand) = input
        val currency = currencyRepository.findById(id) ?: throw EntityNotFoundException("Entity with id=$id not found")

        if (currencyRepository.existsByCode(executableCommand.name) && executableCommand.name != currency.name)
            throw EntityAlreadyExistsException("This currency already exists")

        val updatedCurrency = currency
            .changeCode(executableCommand.code)
            .rename(executableCommand.name)
            .changeSymbol(executableCommand.symbol)

        return currencyRepository.save(updatedCurrency)
    }
}