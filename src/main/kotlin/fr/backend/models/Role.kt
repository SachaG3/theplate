package fr.backend.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import java.util.*

@Entity
open class Role {
    @Id
    open var id: UUID = UUID.randomUUID()

    @Column(nullable = false, length = 60)
    @NotBlank
    open var name: String? = null
}
