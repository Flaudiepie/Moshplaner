package de.moshplaner.backend.application

import de.moshplaner.backend.domain.model.Concert
import de.moshplaner.backend.domain.service.ConcertService
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.UUID

class CreateConcertUseCaseTest {
    private val concertService = mockk<ConcertService>()
    private val useCase = CreateConcertUseCase(concertService)

    @Test
    fun `createConcert should delegate to domain service`() {
        // given
        val name = "Rock Night"
        val venue = "Arena"
        val date = LocalDate.of(2026, 8, 15)
        val concert = Concert(UUID.randomUUID(), name, venue, date)
        every { concertService.createConcert(name, venue, date) } returns concert

        // when
        val result = useCase.createConcert(name, venue, date)

        // then
        result shouldBe concert
        verify(exactly = 1) { concertService.createConcert(name, venue, date) }
    }

    @Test
    fun `getAllConcerts should delegate to domain service`() {
        // given
        val concerts = listOf(Concert(UUID.randomUUID(), "Rock Night", "Arena", LocalDate.of(2026, 8, 15)))
        every { concertService.getAllConcerts() } returns concerts

        // when
        val result = useCase.getAllConcerts()

        // then
        result shouldBe concerts
        verify(exactly = 1) { concertService.getAllConcerts() }
    }
}
