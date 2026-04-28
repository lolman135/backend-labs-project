package labs.expenseservice.persistence.category

import labs.expenseservice.domain.category.Category
import labs.expenseservice.infrastructure.mapper.CategoryMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.util.*

class CategoryRepositoryImplTest {

    private val categoryJpaRepository: CategoryJpaRepository = mock()
    private val categoryMapper: CategoryMapper = mock()
    private val repository = CategoryRepositoryImpl(categoryJpaRepository, categoryMapper)

    @Test
    fun `save should map domain to entity and return domain result`() {
        val categoryId = UUID.randomUUID()
        val domain = Category(categoryId, "Food")
        val entity = CategoryEntity(categoryId, "Food")

        whenever(categoryMapper.toEntity(domain)).thenReturn(entity)
        whenever(categoryJpaRepository.save(entity)).thenReturn(entity)
        whenever(categoryMapper.toDomainFromEntity(entity)).thenReturn(domain)

        val result = repository.save(domain)

        assertThat(result.name).isEqualTo("Food")
        verify(categoryJpaRepository).save(entity)
    }

    @Test
    fun `existsByName should call jpa repository method`() {
        val name = "Salary"
        whenever(categoryJpaRepository.existsCategoryEntityByName(name)).thenReturn(true)

        val result = repository.existsByName(name)

        assertThat(result).isTrue()
        verify(categoryJpaRepository).existsCategoryEntityByName(name)
    }
}