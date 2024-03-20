package fr.backend.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PastOrPresent
import java.util.*

@Entity
@Table(indexes = [Index(unique = true, columnList = "restaurant_id,user_id")])
open class Evaluation {
    @Id
    open var id: UUID = UUID.randomUUID()

    @Column(columnDefinition = "TINYINT(4)")
    open var note: Int? = null

    @Column(columnDefinition = "TEXT")
    @NotBlank
    open var commentclient: String? = null
    @NotBlank
    open var commentadmin: String? = null
    @PastOrPresent
    open var date: Date? = null

    @Column(columnDefinition = "TEXT")
    @NotBlank
    open var commentReponse: String? = null

    @ManyToOne
    open var restaurant: Restaurant? = null

    @ManyToOne
    open var user: User? = null
}