package labs.userservice.application.usecase

import labs.userservice.application.usecase.currencyExternal.CurrencyInfo
import java.util.UUID

interface CurrencyProvider {
    fun currencyExistsById(currencyId: UUID): Boolean
    fun getCurrencyInfoById(currencyId: UUID): CurrencyInfo
}