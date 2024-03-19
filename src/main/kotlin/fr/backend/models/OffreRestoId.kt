package fr.backend.models

import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.*

@Embeddable
open class OffreRestoId(
    open var idDateJ: Date? = null,
    open var idService: UUID? = null,
    open var idRestaurant: UUID? = null
) : Serializable
