package labs.userservice.infrastructure.communication

import labs.userservice.application.usecase.CurrencyProvider
import labs.userservice.application.usecase.currencyExternal.CurrencyInfo
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CurrencyProviderImpl(private val currencyRestClient: CurrencyRestClient) : CurrencyProvider {

    override fun currencyExistsById(currencyId: UUID) =
        try {
            currencyRestClient.getCurrencyById(currencyId)
            true
        } catch (_: IllegalStateException) {
            false
        }


    override fun getCurrencyInfoById(currencyId: UUID): CurrencyInfo {
        val externalCurrency = currencyRestClient.getCurrencyById(currencyId)
        return CurrencyInfo(externalCurrency.id, externalCurrency.code)
    }
}