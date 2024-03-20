package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.NotBlank
import java.util.*

@Entity
open class Guide {
    @Id
    open var id: UUID = UUID.randomUUID()
    @NotBlank
    open var name: String? = null

    @ManyToOne
    open var user: User? = null
}