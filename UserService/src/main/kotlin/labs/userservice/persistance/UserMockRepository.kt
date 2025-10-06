package labs.userservice.persistance

import labs.userservice.domain.User
import labs.userservice.domain.UserRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class UserMockRepository : UserRepository {

    private val userHolder = mutableMapOf<UUID, User>()

    override fun deleteById(id: UUID) {
        userHolder.remove(id)
    }

    override fun save(user: User): User {
        userHolder[user.id] = user
        return user
    }

    override fun findAll() = userHolder.values.toList()


    override fun findById(id: UUID) = userHolder[id]

    override fun existsById(id: UUID) = userHolder.contains(id)

    override fun existsByNameOrEmail(name: String, email: String) =
        userHolder.values.any { it.name.equals(name, ignoreCase = false) || it.email.equals(email, ignoreCase = false)}
}