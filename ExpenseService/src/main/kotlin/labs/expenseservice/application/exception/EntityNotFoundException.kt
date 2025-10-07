package labs.expenseservice.application.exception

class EntityNotFoundException(override val message: String) : IllegalArgumentException(message) {
}