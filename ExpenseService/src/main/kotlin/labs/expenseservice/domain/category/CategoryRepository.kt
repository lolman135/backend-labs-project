package labs.expenseservice.domain.category

import labs.expenseservice.domain.BaseRepository
import java.util.UUID

interface CategoryRepository : BaseRepository<UUID, Category> {
    fun existsByName(name: String): Boolean
}