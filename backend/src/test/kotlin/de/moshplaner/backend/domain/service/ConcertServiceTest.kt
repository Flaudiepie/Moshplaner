package de.moshplaner.backend.domain.service

import de.moshplaner.backend.domain.model.Concert
import de.moshplaner.backend.domain.ports.outbound.ConcertRepository
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.UUID

class ConcertServiceTest {
    private val concertRepository = mockk<ConcertRepository>()
    private val service = ConcertService(concertRepository)

    @Test
    fun `createConcert should save a concert with the given details`() {
        // given
        val captured = slot<Concert>()
        every { concertRepository.save(capture(captured)) } answers { captured.captured }

        // when
        val result = service.createConcert("Rock Night", "Arena", LocalDate.of(2026, 8, 15))

        // then
        result.name shouldBe "Rock Night"
        result.venue shouldBe "Arena"
        result.date shouldBe LocalDate.of(2026, 8, 15)
        result.id shouldNotBe null
        verify(exactly = 1) { concertRepository.save(any()) }
    }

    @Test
    fun `createConcert should assign a unique ID on each call`() {
        // given
        val capturedConcerts = mutableListOf<Concert>()
        every { concertRepository.save(capture(capturedConcerts)) } answers { capturedConcerts.last() }

        // when
        service.createConcert("Concert 1", "Venue A", LocalDate.of(2026, 8, 15))
        service.createConcert("Concert 2", "Venue B", LocalDate.of(2026, 9, 20))

        // then
        capturedConcerts[0].id shouldNotBe capturedConcerts[1].id
    }

    @Test
    fun `getAllConcerts should return all concerts from repository`() {
        // given
        val concerts = listOf(Concert(UUID.randomUUID(), "Rock Night", "Arena", LocalDate.of(2026, 8, 15)))
        every { concertRepository.findAll() } returns concerts

        // when
        val result = service.getAllConcerts()

        // then
        result shouldBe concerts
        verify(exactly = 1) { concertRepository.findAll() }
    }

    @Test
    fun `getAllConcerts should return empty list when no concerts exist`() {
        // given
        every { concertRepository.findAll() } returns emptyList()

        // when
        val result = service.getAllConcerts()

        // then
        result.shouldBeEmpty()
    }
}
