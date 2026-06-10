package de.moshplaner.backend.adapter.inbound.rest

import com.ninjasquad.springmockk.MockkBean
import de.moshplaner.backend.domain.model.Concert
import de.moshplaner.backend.domain.ports.inbound.ConcertAccess
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.time.LocalDate
import java.util.UUID

@WebMvcTest(ConcertController::class)
class ConcertControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var concertAccess: ConcertAccess

    private val apiKey = "secret-key-123"

    @Test
    fun `POST should create concert and return 201`() {
        // given
        val id = UUID.randomUUID()
        val concert = Concert(id, "Rock Night", "Arena", LocalDate.of(2026, 8, 15))
        every { concertAccess.createConcert("Rock Night", "Arena", LocalDate.of(2026, 8, 15)) } returns concert

        // when & then
        mockMvc.post("/api/concerts") {
            header("X-Internal-Api-Key", apiKey)
            contentType = MediaType.APPLICATION_JSON
            content = """{"name":"Rock Night","venue":"Arena","date":"2026-08-15"}"""
        }.andExpect {
            status { isCreated() }
            jsonPath("$.id") { value(id.toString()) }
            jsonPath("$.name") { value("Rock Night") }
            jsonPath("$.venue") { value("Arena") }
            jsonPath("$.date") { value("2026-08-15") }
        }
    }

    @Test
    fun `GET should return all concerts with 200`() {
        // given
        val id = UUID.randomUUID()
        every { concertAccess.getAllConcerts() } returns
            listOf(Concert(id, "Rock Night", "Arena", LocalDate.of(2026, 8, 15)))

        // when & then
        mockMvc.get("/api/concerts") {
            header("X-Internal-Api-Key", apiKey)
            accept(MediaType.APPLICATION_JSON)
        }.andExpect {
            status { isOk() }
            jsonPath("$[0].id") { value(id.toString()) }
            jsonPath("$[0].name") { value("Rock Night") }
            jsonPath("$[0].venue") { value("Arena") }
        }
    }

    @Test
    fun `GET should return empty list when no concerts exist`() {
        // given
        every { concertAccess.getAllConcerts() } returns emptyList()

        // when & then
        mockMvc.get("/api/concerts") {
            header("X-Internal-Api-Key", apiKey)
            accept(MediaType.APPLICATION_JSON)
        }.andExpect {
            status { isOk() }
            jsonPath("$") { isArray() }
            jsonPath("$.length()") { value(0) }
        }
    }

    @Test
    fun `should return 401 when API key is missing`() {
        // when & then
        mockMvc.get("/api/concerts") {
            accept(MediaType.APPLICATION_JSON)
        }.andExpect {
            status { isUnauthorized() }
        }
    }

    @Test
    fun `should return 401 when API key is wrong`() {
        // when & then
        mockMvc.get("/api/concerts") {
            header("X-Internal-Api-Key", "wrong-key")
            accept(MediaType.APPLICATION_JSON)
        }.andExpect {
            status { isUnauthorized() }
        }
    }
}
