package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class TypePlat {
    @Id
    open var id: UUID = UUID.randomUUID()
    open var libelle: String? = null

}

