package de.moshplaner.backend

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SecurityFilter : Filter {

    @Value("\${app.security.internal-api-key}")
    private lateinit var internalApiKey: String

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse

        val apiKeyHeader = httpRequest.getHeader("X-Internal-Api-Key")

        if (apiKeyHeader == internalApiKey) {
            chain.doFilter(request, response)
        } else {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing Internal API Key")
        }
    }
}
