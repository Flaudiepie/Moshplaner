package de.moshplaner.backend.adapter.outbound.persistence.jpa

import de.moshplaner.backend.domain.model.Concert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "concerts")
class ConcertEntity(
    @Id
    val id: UUID,
    val name: String,
    val venue: String,
    val date: LocalDate,
) {
    fun toDomain(): Concert = Concert(id = id, name = name, venue = venue, date = date)

    companion object {
        fun fromDomain(concert: Concert): ConcertEntity =
            ConcertEntity(id = concert.id, name = concert.name, venue = concert.venue, date = concert.date)
    }
}
