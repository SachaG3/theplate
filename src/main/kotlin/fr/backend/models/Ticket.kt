package fr.backend.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity
class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: UUID = UUID.randomUUID()

    @NotBlank
    open var title: String? = null

    @Column(columnDefinition = "TINYINT(2)")
    @NotNull
    open var statut: Int? = 0

    @Column(columnDefinition = "TEXT")
    @NotBlank
    open var description: String? = null

    @ManyToOne
    open var user: User? = null

}