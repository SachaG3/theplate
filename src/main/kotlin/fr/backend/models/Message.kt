package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
class Message {
    /**
     * CREATE TABLE Message(
     *    id INT AUTO_INCREMENT,
     *    contenu TEXT,
     *    idUtilisateur INT NOT NULL,
     *    idTicket INT NOT NULL,
     *    PRIMARY KEY(id),
     *    FOREIGN KEY(idUtilisateur) REFERENCES Utilisateur(id),
     *    FOREIGN KEY(idTicket) REFERENCES Ticket(id)
     * );
     */
    @Id
    open var id: UUID = UUID.randomUUID()
    open var content: String? = null

    @ManyToOne
    open var user: User? = null

    @ManyToOne
    open var ticket: Ticket? = null
}