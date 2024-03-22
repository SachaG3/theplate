package fr.backend.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.util.*

@Entity
open class Plat {
    @Id
    open var id: UUID = UUID.randomUUID()

    @NotBlank
    open var name: String? = null

    @NotNull(message = "L'image ne peut pas être nulle")
    open var image: String? = null

    @Column(columnDefinition = "TEXT")
    @NotBlank
    open var description: String? = null

    @Positive
    open var price: Int? = null

    @NotNull
    open var actif: Boolean? = false

    @NotNull
    open var duJour: Boolean? = false

    @ManyToOne
    open var typePlat: TypePlat? = null

    @ManyToOne
    open var restaurant: Restaurant? = null
}