package labs.currencyservice.web.controller

import labs.currencyservice.application.usecase.CreateCurrencyUseCase
import labs.currencyservice.application.usecase.DeleteCurrencyByIdUseCase
import labs.currencyservice.application.usecase.GetAllCurrenciesUseCase
import labs.currencyservice.application.usecase.GetCurrencyByIdUseCase
import labs.currencyservice.application.usecase.UpdateCurrencyByIdUseCase
import labs.currencyservice.infrastructure.dto.inbound.CurrencyDtoInbound
import labs.currencyservice.infrastructure.dto.outbound.CurrencyDtoOutbound
import labs.currencyservice.infrastructure.mapper.CurrencyMapper
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import java.util.UUID

@RestController
@RequestMapping("/api/v1/currencies")
@Validated
class CurrencyController(
    private val currencyMapper: CurrencyMapper,
    private val createCurrencyUseCase: CreateCurrencyUseCase,
    private val getAllCurrenciesUseCase: GetAllCurrenciesUseCase,
    private val getCurrencyByIdUseCase: GetCurrencyByIdUseCase,
    private val updateCurrencyByIdUseCase: UpdateCurrencyByIdUseCase,
    private val deleteCurrencyByIdUseCase: DeleteCurrencyByIdUseCase
) {

    @PostMapping
    fun save(@Valid @RequestBody inbound: CurrencyDtoInbound): ResponseEntity<CurrencyDtoOutbound> {
        val command = currencyMapper.toCommand(inbound)
        val outbound = currencyMapper.toDto(createCurrencyUseCase.execute(command))
        val location = URI.create("/api/v1/currencies/${outbound.id}")
        return ResponseEntity.created(location).body(outbound)
    }

    @GetMapping
    fun getAll() = ResponseEntity.ok(getAllCurrenciesUseCase.execute(Unit).map { currencyMapper.toDto(it) })

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID) = currencyMapper.toDto(getCurrencyByIdUseCase.execute(id))

    @PutMapping("/{id}")
    fun updateById(
        @PathVariable id: UUID,
        @Valid @RequestBody inbound: CurrencyDtoInbound
    ): ResponseEntity<CurrencyDtoOutbound> {
        val inbound = Pair(id,  currencyMapper.toCommand(inbound))
        val outbound = currencyMapper.toDto(updateCurrencyByIdUseCase.execute(inbound))
        return ResponseEntity.ok(outbound)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: UUID) = deleteCurrencyByIdUseCase.execute(id)
}