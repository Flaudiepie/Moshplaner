package de.moshplaner.backend.adapter.outbound.persistence.jpa

import de.moshplaner.backend.domain.model.Concert
import de.moshplaner.backend.domain.ports.outbound.ConcertRepository
import org.springframework.stereotype.Repository

@Repository
class ConcertPersistenceAdapter(
    private val concertJpaRepository: ConcertJpaRepository,
) : ConcertRepository {
    override fun save(concert: Concert): Concert =
        concertJpaRepository.save(ConcertEntity.fromDomain(concert)).toDomain()

    override fun findAll(): List<Concert> =
        concertJpaRepository.findAll().map { it.toDomain() }
}
