package fr.backend.models

import jakarta.persistence.*
import java.util.*

@Entity
@Table(indexes = [Index(unique = true, columnList = "datej_datej,service_id,restaurant_id")])
open class OffreResto {
    @Id
    open var id: UUID = UUID.randomUUID()

    @Column(nullable = false, columnDefinition = "TINYINT(43)")

    open var nbPlaces: Int? = null

    @ManyToOne
    open var dateJ: DateJ? = null

    @ManyToOne
    open var service: Service? = null

    @ManyToOne
    open var restaurant: Restaurant? = null
}
