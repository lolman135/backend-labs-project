package labs.currencyservice.application.usecase

import labs.currencyservice.domain.Currency
import labs.currencyservice.domain.CurrencyRepository

class GetAllCurrenciesUseCase(private val currencyRepository: CurrencyRepository) : UseCase<Unit, List<Currency>> {

    override fun execute(input: Unit) = currencyRepository.findAll()

}