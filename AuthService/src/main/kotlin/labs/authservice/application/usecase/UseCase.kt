package labs.authservice.application.usecase

interface UseCase<I, O> {
    fun execute(input: I): O
}