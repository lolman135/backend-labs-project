package labs.currencyservice.domain

import java.util.UUID

interface CurrencyRepository {
    fun save(currency: Currency): Currency
    fun findAll(): List<Currency>
    fun findById(id: UUID): Currency?
    fun deleteById(id: UUID)
    fun existsById(id: UUID): Boolean
    fun existsByCode(code: String): Boolean
}