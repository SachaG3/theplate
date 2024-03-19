package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity

open class Preferences {
    @ManyToOne
    @Id
    open var Theme: Theme? = null

    @ManyToOne
    @Id
    open var User: User? = null
}