package labs.authservice.application.exception

class EntityAlreadyExistsException(override val message: String) : Exception(message)