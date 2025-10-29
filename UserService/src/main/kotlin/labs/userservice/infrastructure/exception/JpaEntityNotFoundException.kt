package labs.userservice.infrastructure.exception

import java.lang.Exception

class JpaEntityNotFoundException(override val message: String?) : Exception(message) {
}