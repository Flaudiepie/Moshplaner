package de.moshplaner.backend.domain.model

import java.time.LocalDate
import java.util.UUID

data class Concert(
    val id: UUID,
    val name: String,
    val venue: String,
    val date: LocalDate,
)
