package fr.backend.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PastOrPresent
import java.util.*

@Entity
@Table(indexes = [Index(unique = true, columnList = "restaurant_id,guide_id")])
class EvalOfficielle {
    @Id
    open var id: UUID = UUID.randomUUID()

    @ManyToOne
    open var Guide: Guide? = null

    @ManyToOne
    open var Restaurant: Restaurant? = null

    @Column(columnDefinition = "TINYINT(4)")
    open var note: Int? = null

    @Column(columnDefinition = "TEXT")
    @NotBlank
    open var comment: String? = null

    @PastOrPresent
    open var dateC: Date? = null

}