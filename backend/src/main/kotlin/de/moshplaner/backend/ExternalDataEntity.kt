package de.moshplaner.backend

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "external_data")
data class ExternalDataEntity(
    @Id
    val id: Long = 0,
    val title: String = "",
    val body: String = "",
    val userId: Long = 0
)
