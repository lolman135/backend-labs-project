package labs.userservice.application.usecase.currencyExternal

import labs.userservice.application.exception.EntityNotFoundException
import labs.userservice.application.usecase.CurrencyProvider
import labs.userservice.application.usecase.UseCase
import java.util.UUID

class GetCurrencyInfoUseCase(private val currencyProvider: CurrencyProvider) : UseCase<UUID, CurrencyInfo> {
    override fun execute(input: UUID) = try {
        currencyProvider.getCurrencyInfoById(input)
    } catch (ex: IllegalStateException){
        throw EntityNotFoundException(ex.message!!)
    }
}