package labs.currencyservice.application.usecase

import labs.currencyservice.application.exception.EntityAlreadyExistsException
import labs.currencyservice.domain.Currency
import labs.currencyservice.domain.CurrencyRepository
import java.util.UUID

class CreateCurrencyUseCase(
    private val currencyRepository: CurrencyRepository
) : UseCase<UpsertCurrencyCommand, Currency> {

    override fun execute(input: UpsertCurrencyCommand): Currency {
        if (currencyRepository.existsByCode(input.code))
            throw EntityAlreadyExistsException("Currency already exists")

        val currency = Currency(
            id = UUID.randomUUID(),
            code = input.code,
            name = input.name,
            symbol = input.symbol
        )
        return currencyRepository.save(currency)
    }
}