package labs.currencyservice.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Currency
import java.util.UUID

@Repository
interface CurrencyJpaRepository : JpaRepository<CurrencyEntity, UUID> {
    fun existsCurrencyEntityByCode(code: String): Boolean
}