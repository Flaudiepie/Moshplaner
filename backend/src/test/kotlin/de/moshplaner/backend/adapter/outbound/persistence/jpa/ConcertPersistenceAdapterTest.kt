package de.moshplaner.backend.adapter.outbound.persistence.jpa

import de.moshplaner.backend.domain.model.Concert
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.UUID

class ConcertPersistenceAdapterTest {
    private val concertJpaRepository = mockk<ConcertJpaRepository>()
    private val adapter = ConcertPersistenceAdapter(concertJpaRepository)

    @Test
    fun `save should persist entity and return mapped domain object`() {
        // given
        val concert = Concert(UUID.randomUUID(), "Rock Night", "Arena", LocalDate.of(2026, 8, 15))
        every { concertJpaRepository.save(any()) } returns ConcertEntity.fromDomain(concert)

        // when
        val result = adapter.save(concert)

        // then
        result shouldBe concert
        verify(exactly = 1) { concertJpaRepository.save(any()) }
    }

    @Test
    fun `findAll should return mapped domain objects`() {
        // given
        val id = UUID.randomUUID()
        val entity = ConcertEntity(id, "Metal Night", "Barclays Arena", LocalDate.of(2026, 10, 31))
        every { concertJpaRepository.findAll() } returns listOf(entity)

        // when
        val result = adapter.findAll()

        // then
        result shouldHaveSize 1
        result.first() shouldBe entity.toDomain()
    }

    @Test
    fun `findAll should return empty list when no concerts exist`() {
        // given
        every { concertJpaRepository.findAll() } returns emptyList()

        // when
        val result = adapter.findAll()

        // then
        result.shouldBeEmpty()
    }
}
