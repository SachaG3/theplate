package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
open class Favoris {
    @ManyToOne
    @Id
    open var Restaurant: Restaurant? = null

    @ManyToOne
    @Id
    open var User: User? = null
}