package labs.authservice.application

import java.util.UUID

interface JwtProvider {
    fun generateToken(id: UUID): String
    fun getUserIdFromToken(token: String): UUID
    fun validateToken(token: String): Boolean
}