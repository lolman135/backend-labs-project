package labs.backendlabsproject.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/healthcheck")
class HealthCheckController {

    @GetMapping
    fun checkHealth() = ResponseEntity.ok("Everything is fine!")
}