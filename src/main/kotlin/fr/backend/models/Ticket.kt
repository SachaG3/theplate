package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
class Ticket {
    /**
     *CREATE TABLE Ticket(
     *    id INT AUTO_INCREMENT,
     *    titre VARCHAR(100),
     *    statut TINYINT,
     *    description TEXT,
     *    idUtilisateur INT NOT NULL,
     *    PRIMARY KEY(id),
     *    FOREIGN KEY(idUtilisateur) REFERENCES Utilisateur(id)
     * );
     */
    @Id
    open var id: UUID = UUID.randomUUID()
    open var title: String? = null
    open var status: Int? = null
    open var description: String? = null

    @ManyToOne
    open var user: User? = null

}