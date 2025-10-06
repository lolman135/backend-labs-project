package labs.backendlabsproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HealthCheckService

fun main(args: Array<String>) {
    runApplication<HealthCheckService>(*args)
}
