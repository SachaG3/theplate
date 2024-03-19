package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity

open class Theme {
    @Id
    open var id: UUID = UUID.randomUUID()
    open var libelle: String? = null
}