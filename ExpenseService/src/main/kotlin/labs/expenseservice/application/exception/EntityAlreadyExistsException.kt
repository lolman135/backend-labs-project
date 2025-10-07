package labs.expenseservice.application.exception
class EntityAlreadyExistsException(override val message: String) : IllegalArgumentException(message)