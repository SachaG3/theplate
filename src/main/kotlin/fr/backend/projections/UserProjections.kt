package fr.backend.projections

import fr.backend.models.Message
import fr.backend.models.Restaurant
import fr.backend.models.Role
import fr.backend.models.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.rest.core.config.Projection

@Projection(name = "userProjection", types = [User::class])
interface UserProjection {
    fun getId(): String
    fun getLogin(): String

    @Value("#{target.role}")
    fun getRole(): Role

    @Value("#{target.messages}")
    fun getMessages(): List<Message>

    @Value("#{target.favorites}")
    fun getFavorites(): List<Restaurant>
}
