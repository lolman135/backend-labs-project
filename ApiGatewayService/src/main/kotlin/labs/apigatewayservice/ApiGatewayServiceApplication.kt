package labs.apigatewayservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
@EnableWebFlux
class ApiGatewayServiceApplication

fun main(args: Array<String>) {
    runApplication<ApiGatewayServiceApplication>(*args)
}
