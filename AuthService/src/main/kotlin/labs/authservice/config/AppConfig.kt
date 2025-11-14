package labs.authservice.config

import labs.authservice.application.JwtProvider
import labs.authservice.application.usecase.LoginUseCase
import labs.authservice.application.usecase.RegisterUseCase
import labs.authservice.domain.RoleRepository
import labs.authservice.domain.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class AppConfig {

    @Bean
    fun loginUseCase(
        userRepository: UserRepository,
        authenticationManager: AuthenticationManager,
        jwtProvider: JwtProvider
    ) = LoginUseCase(userRepository, authenticationManager, jwtProvider)

    @Bean
    fun registerUseCase(
        userRepository: UserRepository,
        roleRepository: RoleRepository,
        jwtProvider: JwtProvider,
        passwordEncoder: PasswordEncoder
    ) = RegisterUseCase(passwordEncoder, jwtProvider, userRepository, roleRepository)
}