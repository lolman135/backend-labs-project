package labs.currencyservice.persistence

import labs.currencyservice.domain.Currency
import labs.currencyservice.domain.CurrencyRepository
import labs.currencyservice.infrastructure.mapper.CurrencyMapper
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class CurrencyRepositoryImpl(
    private val currencyJpaRepository: CurrencyJpaRepository,
    private val currencyMapper: CurrencyMapper
) : CurrencyRepository {

    override fun deleteById(id: UUID) {
        currencyJpaRepository.deleteById(id)
    }

    override fun save(currency: Currency): Currency {
        val saved = currencyJpaRepository.save(currencyMapper.toEntity(currency))
        return currencyMapper.toDomainFromEntity(saved)
    }

    override fun findAll(): List<Currency> =
        currencyJpaRepository.findAll().map { currencyMapper.toDomainFromEntity(it) }


    override fun findById(id: UUID): Currency? {
        val entity = currencyJpaRepository.findById(id)
        return entity.map { currencyMapper.toDomainFromEntity(it) }.orElse(null)
    }

    override fun existsById(id: UUID) = currencyJpaRepository.existsById(id)

    override fun existsByCode(code: String) = currencyJpaRepository.existsCurrencyEntityByCode(code)
}