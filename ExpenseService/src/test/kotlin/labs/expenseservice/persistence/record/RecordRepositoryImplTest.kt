package labs.expenseservice.persistence.record

import labs.expenseservice.domain.record.Record
import labs.expenseservice.infrastructure.mapper.RecordMapper
import labs.expenseservice.infrastructure.mapper.RecordMapperHelper
import labs.expenseservice.persistence.category.CategoryEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.time.LocalDateTime
import java.util.*

class RecordRepositoryImplTest {

    private val recordJpaRepository: RecordJpaRepository = mock()
    private val recordMapper: RecordMapper = mock()
    private val recordMapperHelper: RecordMapperHelper = mock()
    private val repository = RecordRepositoryImpl(recordJpaRepository, recordMapper, recordMapperHelper)

    @Test
    fun `save should use helper and return domain`() {
        val recordId = UUID.randomUUID()
        val domain = Record(recordId, UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), 100, UUID.randomUUID())
        val entity = RecordEntity(recordId, null, domain.userId, domain.currencyId, 100, domain.creationTime)

        whenever(recordMapper.toEntity(domain, recordMapperHelper)).thenReturn(entity)
        whenever(recordJpaRepository.save(entity)).thenReturn(entity)
        whenever(recordMapper.toDomainFromEntity(entity)).thenReturn(domain)

        val result = repository.save(domain)

        assertThat(result.id).isEqualTo(recordId)
        verify(recordMapper).toEntity(domain, recordMapperHelper)
    }

    @Test
    fun `findAllByIds should filter records by userId and categoryId`() {
        // GIVEN
        val userId = UUID.randomUUID()
        val categoryId = UUID.randomUUID()

        val record1 = mock<Record> { on { it.userId } doReturn userId; on { it.categoryId } doReturn categoryId }
        val record2 = mock<Record> { on { it.userId } doReturn userId; on { it.categoryId } doReturn UUID.randomUUID() }
        val entity1 = mock<RecordEntity>()
        val entity2 = mock<RecordEntity>()

        whenever(recordJpaRepository.findAll()).thenReturn(listOf(entity1, entity2))
        whenever(recordMapper.toDomainFromEntity(entity1)).thenReturn(record1)
        whenever(recordMapper.toDomainFromEntity(entity2)).thenReturn(record2)

        // WHEN - фильтруем по юзеру и категории
        val result = repository.findAllByIds(userId, categoryId)

        // THEN
        assertThat(result).hasSize(1)
        assertThat(result[0]).isEqualTo(record1)
    }

    @Test
    fun `findAllByIds should return all if criteria are null`() {
        whenever(recordJpaRepository.findAll()).thenReturn(listOf(mock(), mock()))
        whenever(recordMapper.toDomainFromEntity(any())).thenReturn(mock<Record>())

        val result = repository.findAllByIds(null, null)

        assertThat(result).hasSize(2)
    }
}