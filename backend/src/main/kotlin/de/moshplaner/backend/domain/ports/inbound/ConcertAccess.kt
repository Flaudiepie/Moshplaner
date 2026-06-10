package de.moshplaner.backend.domain.ports.inbound

import de.moshplaner.backend.domain.model.Concert
import java.time.LocalDate

interface ConcertAccess {
    fun createConcert(name: String, venue: String, date: LocalDate): Concert

    fun getAllConcerts(): List<Concert>
}
