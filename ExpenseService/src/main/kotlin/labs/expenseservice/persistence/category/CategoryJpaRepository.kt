package labs.expenseservice.persistence.category

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CategoryJpaRepository : JpaRepository<CategoryEntity, UUID>{

    fun existsCategoryEntityByName(name: String): Boolean
}