package labs.currencyservice.application.exception

class EntityAlreadyExistsException(override val message: String) : IllegalArgumentException(message)