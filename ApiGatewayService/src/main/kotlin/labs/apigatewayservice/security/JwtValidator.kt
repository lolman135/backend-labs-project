package labs.apigatewayservice.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.util.UUID
import javax.crypto.SecretKey

@Component
class JwtValidator(
    @param:Value("\${auth.jwt.secret-key}") private val keyResource: Resource,
    @param:Value("\${auth.jwt.access-token-ttl-sec}") private val jwtExpirationMs: Long
) {

    private val secretKey: SecretKey = Keys.hmacShaKeyFor(getKeyAsString().toByteArray())

    fun validateToken(token: String): Boolean = try {
        Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
        true
    } catch (_: Exception){
        false
    }

    fun getUserIdFromToken(token: String): UUID =
        UUID.fromString(Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
        )

    private fun getKeyAsString(): String {
        return Files.readString(keyResource.file.toPath())
            .replace("-----BEGIN KEY-----", "")
            .replace("-----END KEY-----", "")
            .replace("\\s".toRegex(), "")
            .replace("\\n".toRegex(), "")
    }
}