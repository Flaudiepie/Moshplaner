package de.moshplaner.backend.domain.exception

import java.util.UUID

class ConcertNotFoundException(id: UUID) : RuntimeException("Concert not found: $id")
