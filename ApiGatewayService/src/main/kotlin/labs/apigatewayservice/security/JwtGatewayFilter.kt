package labs.apigatewayservice.security

import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.Ordered
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class JwtGatewayFilter(
    private val jwtValidator: JwtValidator
) : GlobalFilter, Ordered {

    private val publicPrefixes = listOf(
        "/api/v1/auth",
        "/healthcheck",
        "/actuator",
        "/swagger-ui",
        "/v3/api-docs"
    )

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val path = exchange.request.uri.path

        // Самый надёжный способ — просто startsWith
        if (publicPrefixes.any { path.startsWith(it) }) {
            return chain.filter(exchange)
        }

        val authHeader = exchange.request.headers["Authorization"]?.firstOrNull()
            ?: return unauthorized(exchange)

        if (!authHeader.startsWith("Bearer ")) {
            return unauthorized(exchange)
        }

        val token = authHeader.substring(7)

        return if (jwtValidator.validateToken(token)) {
            val userId = jwtValidator.getUserIdFromToken(token).toString()
            val mutatedExchange = exchange.mutate()
                .request { it.header("X-User-Id", userId) }
                .build()
            chain.filter(mutatedExchange)
        } else {
            unauthorized(exchange)
        }
    }

    private fun unauthorized(exchange: ServerWebExchange): Mono<Void> {
        exchange.response.statusCode = HttpStatus.UNAUTHORIZED
        return exchange.response.setComplete()
    }

    override fun getOrder() = Ordered.HIGHEST_PRECEDENCE
}