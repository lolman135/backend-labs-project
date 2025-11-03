package labs.expenseservice.persistence.record

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RecordJpaRepository : JpaRepository<RecordEntity, UUID> {
}