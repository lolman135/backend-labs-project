package labs.expenseservice.domain.record

import labs.expenseservice.domain.BaseRepository
import java.util.UUID

interface RecordRepository : BaseRepository<UUID, Record> {
}