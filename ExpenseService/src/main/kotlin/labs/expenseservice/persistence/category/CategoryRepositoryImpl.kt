package labs.expenseservice.persistence.category


import labs.expenseservice.domain.category.Category
import labs.expenseservice.domain.category.CategoryRepository
import labs.expenseservice.infrastructure.mapper.CategoryMapper
import org.springframework.stereotype.Repository
import java.util.UUID
import kotlin.collections.any
import kotlin.collections.contains
import kotlin.collections.set
import kotlin.collections.toList
import kotlin.text.equals

//This repository will be replaced later by JPA Repository
@Repository
class CategoryRepositoryImpl(
    private val categoryJpaRepository: CategoryJpaRepository,
    private val categoryMapper: CategoryMapper
) : CategoryRepository {

    override fun deleteById(id: UUID) {
        categoryJpaRepository.deleteById(id)
    }

    override fun save(domain: Category) =
        categoryMapper.toDomainFromEntity(categoryJpaRepository.save(categoryMapper.toEntity(domain)))

    override fun findAll(): List<Category> = categoryJpaRepository.findAll()
        .map { categoryMapper.toDomainFromEntity(it) }

    override fun findById(id: UUID): Category? {
        val optional = categoryJpaRepository.findById(id)
        return optional.map { categoryMapper.toDomainFromEntity(it) }.orElse(null)
    }

    override fun existsById(id: UUID) = categoryJpaRepository.existsById(id)

    override fun existsByName(name: String) = categoryJpaRepository.existsCategoryEntityByName(name)

}