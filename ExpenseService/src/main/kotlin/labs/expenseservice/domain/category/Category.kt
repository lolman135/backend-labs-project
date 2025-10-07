package labs.expenseservice.domain.category
import java.util.UUID

data class Category(val id: UUID, val name: String) {

    fun rename(newName: String): Category {
        require(newName.isNotBlank()){"Name should not be empty"}
        return copy(name = newName)
    }
}
