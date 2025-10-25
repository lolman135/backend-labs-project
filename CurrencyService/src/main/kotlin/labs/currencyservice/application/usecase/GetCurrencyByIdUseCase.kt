package labs.currencyservice.application.usecase

import labs.currencyservice.application.exception.EntityNotFoundException
import labs.currencyservice.domain.Currency
import labs.currencyservice.domain.CurrencyRepository
import java.util.UUID

class GetCurrencyByIdUseCase(private val currencyRepository: CurrencyRepository) : UseCase<UUID, Currency> {

    override fun execute(input: UUID) = currencyRepository.findById(input)
        ?: throw EntityNotFoundException("Currency with id=$input not found")
}