package labs.backendlabsproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HealthCheckServiceApplication

fun main(args: Array<String>) {
    runApplication<HealthCheckServiceApplication>(*args)
}
