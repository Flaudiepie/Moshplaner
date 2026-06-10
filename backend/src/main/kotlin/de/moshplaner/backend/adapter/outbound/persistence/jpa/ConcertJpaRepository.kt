package de.moshplaner.backend.adapter.outbound.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ConcertJpaRepository : JpaRepository<ConcertEntity, UUID>
