package labs.expenseservice.persistence.record

import labs.expenseservice.domain.record.Record
import labs.expenseservice.domain.record.RecordRepository
import labs.expenseservice.infrastructure.mapper.RecordMapper
import labs.expenseservice.infrastructure.mapper.RecordMapperHelper
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class RecordRepositoryImpl(
    private val recordJpaRepository: RecordJpaRepository,
    private val recordMapper: RecordMapper,
    private val recordMapperHelper: RecordMapperHelper
) : RecordRepository {

    override fun deleteById(id: UUID) {
        recordJpaRepository.deleteById(id)
    }

    override fun save(domain: Record) =
        recordMapper.toDomainFromEntity(recordJpaRepository.save(recordMapper.toEntity(domain, recordMapperHelper)))

    override fun findAll() = recordJpaRepository.findAll().map { recordMapper.toDomainFromEntity(it) }

    override fun findById(id: UUID): Record? {
        val optional = recordJpaRepository.findById(id)
        return optional.map { recordMapper.toDomainFromEntity(it) }.orElse(null)
    }

    override fun existsById(id: UUID) = recordJpaRepository.existsById(id)

    override fun findAllByIds(userId: UUID?, categoryId: UUID?): List<Record> {
        return recordJpaRepository.findAll().map { recordMapper.toDomainFromEntity(it) }
            .filter { record ->
                userId?.let { record.userId == it } ?: true
            }
            .filter { record ->
                categoryId?.let { record.categoryId == it } ?: true
            }
    }
}