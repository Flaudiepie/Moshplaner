package de.moshplaner.backend.integration

import de.moshplaner.backend.adapter.inbound.rest.ConcertRequest
import de.moshplaner.backend.adapter.inbound.rest.ConcertResponse
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.time.LocalDate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class ConcertIntegrationTest {
    companion object {
        @Container
        @ServiceConnection
        val postgres = PostgreSQLContainer("postgres:15-alpine")
    }

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    private fun authHeaders(): HttpHeaders =
        HttpHeaders().apply {
            set("X-Internal-Api-Key", "secret-key-123")
            contentType = MediaType.APPLICATION_JSON
        }

    @Test
    fun `should create a concert and retrieve it via GET`() {
        // given
        val request = ConcertRequest("Metal Night", "Barclays Arena", LocalDate.of(2026, 10, 31))

        // when - create
        val createResponse = restTemplate.exchange(
            "/api/concerts",
            HttpMethod.POST,
            HttpEntity(request, authHeaders()),
            ConcertResponse::class.java,
        )

        // then - 201 with body
        createResponse.statusCode shouldBe HttpStatus.CREATED
        val created = createResponse.body!!
        created.name shouldBe "Metal Night"
        created.venue shouldBe "Barclays Arena"
        created.date shouldBe LocalDate.of(2026, 10, 31)
        created.id shouldNotBe null

        // when - get all
        val getResponse = restTemplate.exchange(
            "/api/concerts",
            HttpMethod.GET,
            HttpEntity<Unit>(authHeaders()),
            Array<ConcertResponse>::class.java,
        )

        // then - concert is present
        getResponse.statusCode shouldBe HttpStatus.OK
        val concerts = getResponse.body!!.toList()
        concerts.shouldNotBeEmpty()
        concerts.any { it.name == "Metal Night" } shouldBe true
    }

    @Test
    fun `should return 401 for requests without API key`() {
        val response = restTemplate.getForEntity("/api/concerts", String::class.java)
        response.statusCode shouldBe HttpStatus.UNAUTHORIZED
    }

    @Test
    fun `should return 401 for requests with wrong API key`() {
        val headers = HttpHeaders().apply { set("X-Internal-Api-Key", "wrong-key") }
        val response = restTemplate.exchange(
            "/api/concerts",
            HttpMethod.GET,
            HttpEntity<Unit>(headers),
            String::class.java,
        )
        response.statusCode shouldBe HttpStatus.UNAUTHORIZED
    }
}
