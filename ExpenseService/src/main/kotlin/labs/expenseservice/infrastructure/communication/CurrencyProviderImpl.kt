package labs.expenseservice.infrastructure.communication

import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.application.useCase.CurrencyProvider
import labs.expenseservice.application.useCase.external.CurrencyInfo
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CurrencyProviderImpl(
    private val currencyRestClient: CurrencyRestClient
) : CurrencyProvider {

    override fun currencyExistsById(currencyId: UUID) =
        try {
            currencyRestClient.getCurrencyById(currencyId = currencyId)
            true
        } catch (ex: IllegalStateException){
            false
        }

    override fun getCurrencyInfoById(currencyId: UUID): CurrencyInfo {
        try {
            val currencyDto = currencyRestClient.getCurrencyById(currencyId)
            return CurrencyInfo(currencyDto.id, currencyDto.code)
        } catch (ex: IllegalStateException){
            throw EntityNotFoundException(ex.message!!)
        }
    }
}