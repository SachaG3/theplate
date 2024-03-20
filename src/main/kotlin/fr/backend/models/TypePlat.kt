package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import java.util.*

@Entity
class TypePlat {
    @Id
    open var id: UUID = UUID.randomUUID()
    @NotBlank
    open var libelle: String? = null

}

