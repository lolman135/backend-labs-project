package labs.userservice.application.exception

class EntityAlreadyExistsException(override val message: String) : IllegalArgumentException(message)