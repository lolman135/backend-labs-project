package labs.currencyservice.persistence

import labs.currencyservice.domain.Currency
import labs.currencyservice.infrastructure.mapper.CurrencyMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.util.*

class CurrencyRepositoryImplTest {

    private val jpaRepository: CurrencyJpaRepository = mock()
    private val mapper: CurrencyMapper = mock()
    private val repository = CurrencyRepositoryImpl(jpaRepository, mapper)

    @Test
    fun `save should map and delegate to jpa`() {
        val id = UUID.randomUUID()
        val domain = Currency(id, "USD", "Dollar", "$")
        val entity = CurrencyEntity(id, "USD", "Dollar", "$")

        whenever(mapper.toEntity(domain)).thenReturn(entity)
        whenever(jpaRepository.save(entity)).thenReturn(entity)
        whenever(mapper.toDomainFromEntity(entity)).thenReturn(domain)

        val result = repository.save(domain)

        assertThat(result.code).isEqualTo("USD")
        verify(jpaRepository).save(entity)
    }

    @Test
    fun `findById should return null if Optional is empty`() {
        val id = UUID.randomUUID()
        whenever(jpaRepository.findById(id)).thenReturn(Optional.empty())

        val result = repository.findById(id)

        assertThat(result).isNull()
    }

    @Test
    fun `findAll should return list of domain currencies`() {
        val entities = listOf(
            CurrencyEntity(UUID.randomUUID(), "A", "N1", "S1"),
            CurrencyEntity(UUID.randomUUID(), "B", "N2", "S2")
        )
        whenever(jpaRepository.findAll()).thenReturn(entities)
        whenever(mapper.toDomainFromEntity(any())).thenReturn(mock<Currency>())

        val result = repository.findAll()

        assertThat(result).hasSize(2)
        verify(mapper, times(2)).toDomainFromEntity(any())
    }
}