package labs.expenseservice.application.useCase

import labs.expenseservice.application.useCase.external.CurrencyInfo
import java.util.UUID

interface CurrencyProvider {
    fun currencyExistsById(currencyId: UUID): Boolean
    fun getCurrencyInfoById(currencyId: UUID): CurrencyInfo
}