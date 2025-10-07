package labs.expenseservice.persistence.repository

import labs.expenseservice.domain.record.Record
import labs.expenseservice.domain.record.RecordRepository
import org.springframework.stereotype.Repository
import java.util.UUID

//This repository will be replaced later by JPA Repository
@Repository
class RecordMockRepository : RecordRepository {

    private val recordHolder = mutableMapOf<UUID, Record>()

    override fun deleteById(id: UUID) {
        recordHolder.remove(id)
    }

    override fun save(domain: Record): Record {
        recordHolder[domain.id] = domain
        return domain
    }

    override fun findAll() = recordHolder.values.toList()

    override fun findById(id: UUID) = recordHolder[id]

    override fun existsById(id: UUID) = recordHolder.contains(id)
}