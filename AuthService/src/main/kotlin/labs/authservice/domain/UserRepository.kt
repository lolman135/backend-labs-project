package labs.authservice.domain

import java.util.UUID

interface UserRepository {

    fun save(user: User): User
    fun findById(id: UUID): User
    fun existsById(id: UUID): Boolean
    fun existsByNameOrEmail(name: String, email: String): Boolean
}