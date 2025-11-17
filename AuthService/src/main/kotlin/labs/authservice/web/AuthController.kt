package labs.authservice.web

import jakarta.validation.Valid
import labs.authservice.application.usecase.LoginUseCase
import labs.authservice.application.usecase.RegisterUseCase
import labs.authservice.infrastructure.dto.request.LoginDtoRequest
import labs.authservice.infrastructure.dto.request.RegisterDtoRequest
import labs.authservice.infrastructure.dto.response.TokenDtoResponse
import labs.authservice.infrastructure.mapper.AuthMapper
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
@Validated
class AuthController(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
    private val authMapper: AuthMapper
) {

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginDtoRequest): ResponseEntity<TokenDtoResponse>{
        val token = loginUseCase.execute(authMapper.toLoginCommand(request))
        return ResponseEntity.ok(TokenDtoResponse(token = token))
    }

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterDtoRequest): ResponseEntity<TokenDtoResponse>{
        val token = registerUseCase.execute(authMapper.toRegisterCommand(request))
        return ResponseEntity.ok(TokenDtoResponse(token = token))
    }
}