package labs.userservice.config

import labs.userservice.application.usecase.CurrencyProvider
import labs.userservice.application.usecase.currencyExternal.GetCurrencyInfoUseCase
import labs.userservice.application.usecase.role.CreateRoleUseCase
import labs.userservice.application.usecase.role.DeleteRoleByIdUseCase
import labs.userservice.application.usecase.role.FindAllRolesUseCase
import labs.userservice.application.usecase.role.FindRoleByIdUseCase
import labs.userservice.application.usecase.role.UpdateRoleByIdUseCase
import labs.userservice.application.usecase.user.CreateUserUseCase
import labs.userservice.application.usecase.user.DeleteUserByIdUseCase
import labs.userservice.application.usecase.user.FindAllUsersUseCase
import labs.userservice.application.usecase.user.FindUserByIdUseCase
import labs.userservice.application.usecase.user.UpdateUserByIdUseCase
import labs.userservice.domain.RoleRepository
import labs.userservice.domain.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class AppConfig {

    @Bean
    fun restTemplate() = RestTemplate()

    //users
    @Bean
    fun createUserUseCase(
        userRepository: UserRepository,
        roleRepository: RoleRepository,
        currencyProvider: CurrencyProvider
    ) = CreateUserUseCase(userRepository, roleRepository, currencyProvider)

    @Bean
    fun findUserByIdUseCase(userRepository: UserRepository) = FindUserByIdUseCase(userRepository)

    @Bean
    fun findAllUsersUseCase(userRepository: UserRepository) = FindAllUsersUseCase(userRepository)

    @Bean
    fun deleteUserByIdUseCase(userRepository: UserRepository) = DeleteUserByIdUseCase(userRepository)

    @Bean
    fun updateUserByIdUseCase(
        userRepository: UserRepository,
        roleRepository: RoleRepository,
        currencyProvider: CurrencyProvider
    ) = UpdateUserByIdUseCase(userRepository, roleRepository, currencyProvider)

    //roles
    @Bean
    fun createRoleUseCase(roleRepository: RoleRepository) = CreateRoleUseCase(roleRepository)

    @Bean
    fun findAllRolesUseCase(roleRepository: RoleRepository) = FindAllRolesUseCase(roleRepository)

    @Bean
    fun findRoleByIdUseCase(roleRepository: RoleRepository) = FindRoleByIdUseCase(roleRepository)

    @Bean
    fun updateRoleByIdUseCase(roleRepository: RoleRepository) = UpdateRoleByIdUseCase(roleRepository)

    @Bean
    fun deleteRoleByIdUseCase(roleRepository: RoleRepository) = DeleteRoleByIdUseCase(roleRepository)

    //external
    @Bean
    fun getCurrencyInfoUseCase(currencyProvider: CurrencyProvider) = GetCurrencyInfoUseCase(currencyProvider)
}