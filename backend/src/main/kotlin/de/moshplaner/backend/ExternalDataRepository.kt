package de.moshplaner.backend

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExternalDataRepository : JpaRepository<ExternalDataEntity, Long>
