package labs.userservice.config

import labs.userservice.application.usecase.user.CreateUserUseCase
import labs.userservice.application.usecase.user.DeleteUserByIdUseCase
import labs.userservice.application.usecase.user.FindAllUsersUseCase
import labs.userservice.application.usecase.user.FindUserByIdUseCase
import labs.userservice.application.usecase.user.UpdateUserByIdUseCase
import labs.userservice.domain.Role
import labs.userservice.domain.RoleRepository
import labs.userservice.domain.UserRepository
import labs.userservice.persistance.RoleMockRepository
import labs.userservice.persistance.UserMockRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean fun createUserUseCase(userRepository: UserRepository) = CreateUserUseCase(userRepository)
    @Bean fun findUserByIdUseCase(userRepository: UserRepository) = FindUserByIdUseCase(userRepository)
    @Bean fun findAllUsersUseCase(userRepository: UserRepository) = FindAllUsersUseCase(userRepository)
    @Bean fun deleteUserByIdUseCase(userRepository: UserRepository) = DeleteUserByIdUseCase(userRepository)
    @Bean fun updateUserByIdUseCase(userRepository: UserRepository) = UpdateUserByIdUseCase(userRepository)
}