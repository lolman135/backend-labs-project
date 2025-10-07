package labs.expenseservice.persistence.repository


import labs.expenseservice.domain.category.Category
import labs.expenseservice.domain.category.CategoryRepository
import org.springframework.stereotype.Repository
import java.util.UUID
import kotlin.collections.any
import kotlin.collections.contains
import kotlin.collections.set
import kotlin.collections.toList
import kotlin.text.equals

//This repository will be replaced later by JPA Repository
@Repository
class CategoryMockRepository : CategoryRepository {

    private val categoryHolder = mutableMapOf<UUID, Category>()

    override fun deleteById(id: UUID) {
        categoryHolder.remove(id)
    }

    override fun save(domain: Category): Category {
        categoryHolder[domain.id] = domain
        return domain
    }

    override fun findAll(): List<Category> = categoryHolder.values.toList()

    override fun findById(id: UUID) = categoryHolder[id]

    override fun existsById(id: UUID) = categoryHolder.contains(id)

    override fun existsByName(name: String) =
        categoryHolder.values.any { it.name.equals(name, ignoreCase = true)}

}