package de.moshplaner.backend.adapter.inbound.rest

import de.moshplaner.backend.domain.model.Concert
import java.time.LocalDate
import java.util.UUID

data class ConcertResponse(
    val id: UUID,
    val name: String,
    val venue: String,
    val date: LocalDate,
) {
    companion object {
        fun fromDomain(concert: Concert): ConcertResponse =
            ConcertResponse(id = concert.id, name = concert.name, venue = concert.venue, date = concert.date)
    }
}
