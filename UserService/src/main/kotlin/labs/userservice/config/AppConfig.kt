package labs.userservice.config

import labs.userservice.application.usecase.CreateUserUseCase
import labs.userservice.application.usecase.DeleteUserByIdUseCase
import labs.userservice.application.usecase.FindAllUsersUseCase
import labs.userservice.application.usecase.FindUserByIdUseCase
import labs.userservice.application.usecase.UpdateUserByIdUseCase
import labs.userservice.domain.UserRepository
import labs.userservice.persistance.UserMockRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean fun userRepository(): UserRepository = UserMockRepository()
    @Bean fun createUserUseCase(userRepository: UserRepository) = CreateUserUseCase(userRepository)
    @Bean fun findUserByIdUseCase(userRepository: UserRepository) = FindUserByIdUseCase(userRepository)
    @Bean fun findAllUsersUseCase(userRepository: UserRepository) = FindAllUsersUseCase(userRepository)
    @Bean fun deleteUserByIdUseCase(userRepository: UserRepository) = DeleteUserByIdUseCase(userRepository)
    @Bean fun updateUserByIdUseCase(userRepository: UserRepository) = UpdateUserByIdUseCase(userRepository)
}