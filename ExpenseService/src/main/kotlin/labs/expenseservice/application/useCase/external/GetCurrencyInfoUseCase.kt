package labs.expenseservice.application.useCase.external

import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.application.useCase.CurrencyProvider
import labs.expenseservice.application.useCase.UseCase
import java.util.UUID

class GetCurrencyInfoUseCase(private val currencyProvider: CurrencyProvider) : UseCase<UUID, CurrencyInfo> {
    override fun execute(input: UUID) = try {
        currencyProvider.getCurrencyInfoById(input)
    } catch (ex: IllegalStateException){
        throw EntityNotFoundException(ex.message!!)
    }
}