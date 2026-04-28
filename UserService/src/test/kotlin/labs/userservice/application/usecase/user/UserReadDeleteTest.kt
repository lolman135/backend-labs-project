package labs.userservice.application.usecase.user

import labs.userservice.application.exception.EntityNotFoundException
import labs.userservice.domain.User
import labs.userservice.domain.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.UUID

class UserReadDeleteTest {

    private val userRepository: UserRepository = mock()

    @Test
    fun `FindUserByIdUseCase should throw exception if user not found`() {
        val useCase = FindUserByIdUseCase(userRepository)
        val id = UUID.randomUUID()
        whenever(userRepository.findById(id)).thenReturn(null)

        val exception = assertThrows<EntityNotFoundException> {
            useCase.execute(id)
        }
        assertThat(exception.message).contains("User with id=$id not found")
    }

    @Test
    fun `FindAllUsersUseCase should return list of users`() {
        val useCase = FindAllUsersUseCase(userRepository)
        val users = listOf(
            User(UUID.randomUUID(), "U1", "e1", "p1", listOf(), null),
            User(UUID.randomUUID(), "U2", "e2", "p2", listOf(), null)
        )
        whenever(userRepository.findAll()).thenReturn(users)

        val result = useCase.execute(Unit)

        assertThat(result).hasSize(2)
        assertThat(result).isEqualTo(users)
    }

    @Test
    fun `DeleteUserByIdUseCase should call repository`() {
        val useCase = DeleteUserByIdUseCase(userRepository)
        val id = UUID.randomUUID()

        useCase.execute(id)

        verify(userRepository).deleteById(id)
    }
}