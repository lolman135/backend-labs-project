package labs.currencyservice.application.exception

class EntityNotFoundException(override val message: String) : IllegalArgumentException(message)