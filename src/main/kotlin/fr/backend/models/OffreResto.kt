package fr.backend.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.PositiveOrZero

@Entity
open class OffreResto {

    @Column(nullable = false,columnDefinition = "TINYINT(43)")
    @PositiveOrZero
    open var nbPlaces: Int? = null

    @ManyToOne
    @Id
    open var dateJ: DateJ? = null

    @ManyToOne
    @Id
    open var service: Service? = null

    @ManyToOne
    @Id
    open var restaurant: Restaurant? = null
}
