package fr.backend.models

import jakarta.persistence.*
import java.util.*

@Entity
open class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: UUID? = null

    @Temporal(TemporalType.TIMESTAMP)
    open var dateR: Date? = null

    open var statut: Int? = null

    open var nbPlaces: Int? = null

    @Column(columnDefinition = "TEXT")
    open var commentaires: String? = null

    @ManyToOne
    open var utilisateur: User? = null

    @ManyToOne
    open var offreResto: OffreResto? = null

}
