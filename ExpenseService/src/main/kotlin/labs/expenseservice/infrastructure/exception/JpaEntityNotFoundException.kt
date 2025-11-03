package labs.expenseservice.infrastructure.exception

import java.lang.Exception

class JpaEntityNotFoundException(override val message: String) : Exception(message) {
}