package fr.backend.models

import jakarta.persistence.*
import java.util.*

@Entity
@Table(indexes = [Index(unique = true, columnList = "guide_id, restaurant_id")])
open class EvalOfficielle {
    @Id
    open var id: UUID = UUID.randomUUID()
    @ManyToOne
    open var Guide: Guide? = null

    @ManyToOne
    open var Restaurant: Restaurant? = null
    @Column(columnDefinition = "TINYINT(4)")
    open var note: Int? = null
    @Column(columnDefinition = "TEXT")
    open var comment: String? = null
    open var dateC: Date? = null

}