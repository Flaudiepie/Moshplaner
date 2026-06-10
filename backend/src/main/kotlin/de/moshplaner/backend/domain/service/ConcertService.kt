package de.moshplaner.backend.domain.service

import de.moshplaner.backend.domain.model.Concert
import de.moshplaner.backend.domain.model.ConcertCreatedEvent
import de.moshplaner.backend.domain.ports.outbound.ConcertRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.UUID

@Service
class ConcertService(
    private val concertRepository: ConcertRepository,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(ConcertService::class.java)
    }

    fun createConcert(name: String, venue: String, date: LocalDate): Concert {
        val concert = Concert(id = UUID.randomUUID(), name = name, venue = venue, date = date)
        val saved = concertRepository.save(concert)
        val event = ConcertCreatedEvent(concert = saved)
        logger.info("Domain event raised: $event")
        return saved
    }

    fun getAllConcerts(): List<Concert> = concertRepository.findAll()
}
