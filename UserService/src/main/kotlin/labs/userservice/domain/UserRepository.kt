package labs.userservice.domain

import java.util.UUID

interface UserRepository {
    fun save(user: User): User
    fun findAll(): List<User>
    fun findById(id: UUID): User?
    fun deleteById(id: UUID)
    fun existsById(id: UUID): Boolean
    fun existsByNameOrEmail(name: String, email: String): Boolean
}