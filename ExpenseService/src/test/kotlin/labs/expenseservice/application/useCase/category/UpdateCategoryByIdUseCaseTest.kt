package labs.expenseservice.application.useCase.category

import labs.expenseservice.application.exception.EntityAlreadyExistsException
import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.domain.category.Category
import labs.expenseservice.domain.category.CategoryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.UUID

class UpdateCategoryByIdUseCaseTest {

    private val repository: CategoryRepository = mock()
    private val useCase = UpdateCategoryByIdUseCase(repository)

    @Test
    fun `should update category name successfully`() {
        val id = UUID.randomUUID()
        val existingCategory = Category(id, "Old Name")
        val command = UpsertCategoryCommand("New Name")

        whenever(repository.findById(id)).thenReturn(existingCategory)
        whenever(repository.existsByName("New Name")).thenReturn(false)
        whenever(repository.save(any())).thenAnswer { it.arguments[0] }

        val result = useCase.execute(id to command)

        assertThat(result.name).isEqualTo("New Name")
        verify(repository).save(any())
    }

    @Test
    fun `should throw exception if category to update not found`() {
        val id = UUID.randomUUID()
        whenever(repository.findById(id)).thenReturn(null)

        assertThrows<EntityNotFoundException> {
            useCase.execute(id to UpsertCategoryCommand("Any"))
        }
    }
}