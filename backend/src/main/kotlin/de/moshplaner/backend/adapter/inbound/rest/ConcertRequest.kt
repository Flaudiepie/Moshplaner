package de.moshplaner.backend.adapter.inbound.rest

import java.time.LocalDate

data class ConcertRequest(
    val name: String,
    val venue: String,
    val date: LocalDate,
)
