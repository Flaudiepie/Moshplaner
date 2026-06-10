package de.moshplaner.backend.domain.ports.outbound

import de.moshplaner.backend.domain.model.Concert

interface ConcertRepository {
    fun save(concert: Concert): Concert

    fun findAll(): List<Concert>
}
