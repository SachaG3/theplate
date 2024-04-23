package fr.backend.faker

import com.github.javafaker.Faker
import fr.backend.models.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit

@Component
class DataFaker {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder // Assurez-vous que l'injection se fait correctement.

    fun generateRole(): List<Role> {
        val roles = mutableListOf<Role>()
        val roleName = listOf("ADMIN", "USER")
        for (name in roleName) {
            val role = Role()
            role.name = name
            roles.add(role)
        }
        return roles
    }

    fun generateUser(count: Int): List<User> {
        val users = mutableListOf<User>()
        val faker = Faker()
        for (i in 1..count) {
            val user = User().apply {
                login = faker.name().username()
                email = faker.internet().emailAddress()
                password = passwordEncoder.encode("aze")
                latitude = faker.address().latitude()
                longitude = faker.address().longitude()
            }
            users.add(user)
        }
        return users
    }

    fun generateThemes(count: Int): List<Theme> {
        val themes = mutableListOf<Theme>()
        val faker = Faker()

        repeat(count) {
            val theme = Theme().apply {
                libelle = faker.book().genre()
            }
            themes.add(theme)
        }

        return themes
    }

    fun generateRestaurants(count: Int, themes: List<Theme>): List<Restaurant> {
        val faker = Faker()
        val restaurants = mutableListOf<Restaurant>()

        repeat(count) {
            val restaurant = Restaurant().apply {
                name = faker.company().name()
                decription = faker.lorem().paragraph()
                address1 = faker.address().streetAddress()
                address2 = faker.address().secondaryAddress()
                postalCode = faker.address().zipCode()
                city = faker.address().city()
                latitude = "${faker.number().randomDouble(6, -85, 85)}"
                longitude = "${faker.number().randomDouble(6, -180, 180)}"
                approuve = faker.bool().bool()
                theme = themes.random()
            }
            restaurants.add(restaurant)
        }

        return restaurants
    }

    fun generateTypePlats(count: Int): List<TypePlat> {
        val typePlats = mutableListOf<TypePlat>()
        val faker = Faker()

        repeat(count) {
            val typePlat = TypePlat().apply {
                // L'id est généré automatiquement
                libelle = faker.food().dish() // Utiliser une catégorie appropriée de Faker
            }
            typePlats.add(typePlat)
        }

        return typePlats
    }

    fun generatePlats(count: Int, typePlats: List<TypePlat>, restaurants: List<Restaurant>): List<Plat> {
        val plats = mutableListOf<Plat>()
        val faker = Faker()

        repeat(count) {
            val plat = Plat().apply {
                name = faker.food().ingredient()
                image = "https://example.com/image/${UUID.randomUUID()}.jpg"
                description = faker.lorem().sentence()
                price = faker.number().numberBetween(5, 50)
                actif = faker.bool().bool()
                duJour = faker.bool().bool()
                typePlat = typePlats.random()
                restaurant = restaurants.random()
            }
            plats.add(plat)
        }

        return plats
    }

    fun generateEvaluations(count: Int, restaurants: List<Restaurant>, users: List<User>): List<Evaluation> {
        val evaluations = mutableListOf<Evaluation>()
        val faker = Faker()

        repeat(count) {
            val evaluation = Evaluation().apply {
                note = faker.number().numberBetween(0, 4)
                commentclient = faker.lorem().paragraph()
                commentadmin = faker.lorem().paragraph()
                date = faker.date().past(365, TimeUnit.DAYS)
                commentReponse = faker.lorem().paragraph()
                restaurant = restaurants.random()
                user = users.random()
            }
            evaluations.add(evaluation)
        }

        return evaluations
    }

    fun generateGuides(count: Int, users: List<User>): List<Guide> {
        val guides = mutableListOf<Guide>()
        val faker = Faker()

        repeat(count) {
            val guide = Guide().apply {
                name = faker.name().fullName()
                user = users.random()
            }
            guides.add(guide)
        }

        return guides
    }

    fun generateEvalOfficielles(count: Int, guides: List<Guide>, restaurants: List<Restaurant>): List<EvalOfficielle> {
        val evalOfficielles = mutableListOf<EvalOfficielle>()
        val faker = Faker()

        repeat(count) {
            val evalOfficielle = EvalOfficielle().apply {
                Guide = guides.random()
                Restaurant = restaurants.random()
                note = faker.number().numberBetween(0, 4)
                comment = faker.lorem().paragraph()
                dateC = faker.date().past(365 * 2, TimeUnit.DAYS)
            }
            evalOfficielles.add(evalOfficielle)
        }

        return evalOfficielles
    }

    fun generateOffreRestos(
        count: Int,
        dateJs: List<DateJ>,
        services: List<Service>,
        restaurants: List<Restaurant>
    ): List<OffreResto> {
        val offreRestos = mutableListOf<OffreResto>()
        val faker = Faker()

        repeat(count) {
            val offreResto = OffreResto().apply {
                nbPlaces = faker.number().numberBetween(1, 20)
                dateJ = dateJs.random()
                service = services.random()
                restaurant = restaurants.random()
            }
            offreRestos.add(offreResto)
        }

        return offreRestos
    }

    fun generatePreferences(count: Int, themes: List<Theme>, users: List<User>): List<Preferences> {
        val preferences = mutableListOf<Preferences>()

        repeat(count) {
            val preference = Preferences().apply {
                Theme = themes.random()
                User = users.random()
            }
            preferences.add(preference)
        }

        return preferences
    }

    fun generateTickets(count: Int, users: List<User>): List<Ticket> {
        val tickets = mutableListOf<Ticket>()
        val faker = Faker()

        repeat(count) {
            val ticket = Ticket().apply {
                title = faker.lorem().sentence()
                statut = faker.number().numberBetween(0, 2) // 0: ouvert, 1: en cours, 2: fermé
                description = faker.lorem().paragraph()
                user = users.random()
            }
            tickets.add(ticket)
        }

        return tickets
    }

    fun generateFavoris(count: Int, restaurants: List<Restaurant>, users: List<User>): List<Favoris> {
        val favoris = mutableListOf<Favoris>()

        repeat(count) {
            val favori = Favoris().apply {
                Restaurant = restaurants.random()
                User = users.random()
            }
            favoris.add(favori)
        }

        return favoris
    }


}

