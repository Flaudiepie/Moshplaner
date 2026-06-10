package de.moshplaner.backend.application

import de.moshplaner.backend.domain.model.Concert
import de.moshplaner.backend.domain.ports.inbound.ConcertAccess
import de.moshplaner.backend.domain.service.ConcertService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CreateConcertUseCase(
    private val concertService: ConcertService,
) : ConcertAccess {
    override fun createConcert(name: String, venue: String, date: LocalDate): Concert =
        concertService.createConcert(name, venue, date)

    override fun getAllConcerts(): List<Concert> = concertService.getAllConcerts()
}
