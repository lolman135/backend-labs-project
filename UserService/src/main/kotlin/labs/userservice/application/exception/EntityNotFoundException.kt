package labs.userservice.application.exception

class EntityNotFoundException(override val message: String) : IllegalArgumentException(message)