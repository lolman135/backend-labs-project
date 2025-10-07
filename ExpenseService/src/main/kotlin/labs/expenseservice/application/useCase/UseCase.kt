package labs.expenseservice.application.useCase

interface UseCase<C, R> {
    fun execute(command: C): R
}