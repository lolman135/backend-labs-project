package labs.expenseservice.application.useCase.category

import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.domain.category.Category
import labs.expenseservice.domain.category.CategoryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.UUID

class CategoryReadDeleteTest {

    private val repository: CategoryRepository = mock()

    @Test
    fun `GetCategoryByIdUseCase should return category`() {
        val useCase = GetCategoryByIdUseCase(repository)
        val id = UUID.randomUUID()
        val category = Category(id, "Health")
        whenever(repository.findById(id)).thenReturn(category)

        val result = useCase.execute(id)

        assertThat(result).isEqualTo(category)
    }

    @Test
    fun `GetAllCategoriesUseCase should return list`() {
        val useCase = GetAllCategoriesUseCase(repository)
        val categories = listOf(Category(UUID.randomUUID(), "A"), Category(UUID.randomUUID(), "B"))
        whenever(repository.findAll()).thenReturn(categories)

        val result = useCase.execute(Unit)

        assertThat(result).hasSize(2)
    }

    @Test
    fun `DeleteCategoryByIdUseCase should call repository delete`() {
        val useCase = DeleteCategoryByIdUseCase(repository)
        val id = UUID.randomUUID()

        useCase.execute(id)

        verify(repository).deleteById(id)
    }
}