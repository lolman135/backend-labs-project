package labs.userservice.application.usecase

interface UseCase<I, O> {
    fun execute(input: I): O
}