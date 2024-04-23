package fr.backend.controllers

import fr.backend.faker.DataFaker
import fr.backend.models.*
import fr.backend.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/faker")
class FakerController {
    @Autowired
    private lateinit var userFaker: DataFaker

    @Autowired
    private lateinit var UserRepository: UserRepository

    @Autowired
    private lateinit var RoleRepository: RoleRepository

    @Autowired
    private lateinit var themeRepository: ThemeRepository

    @Autowired
    private lateinit var restaurantRepository: RestaurantRepository

    @Autowired
    private lateinit var typePlatRepository: TypePlatRepository

    @Autowired
    private lateinit var platRepository: PlatRepository

    @Autowired
    private lateinit var evaluationRepository: EvaluationRepository

    @Autowired
    private lateinit var guideRepository: GuideRepository

    @Autowired
    private lateinit var evalOfficielleRepository: EvalOfficielleRepository


    @GetMapping("/roles")
    fun generateRoles() {
        val roles = userFaker.generateRole()
        RoleRepository.saveAll(roles)
    }

    @GetMapping("/users/{count}")
    fun generate(@PathVariable count: Int) {
        val users = userFaker.generateUser(count)
        users.forEach {
            it.role = RoleRepository.findByName("ROLE_USER")!!
        }
        UserRepository.saveAll(users)
    }

    @GetMapping("/generate/themes/{count}")
    fun generateThemes(@PathVariable count: Int) {
        val themes = userFaker.generateThemes(count)
        themeRepository.saveAll(themes)
    }

    @GetMapping("/restaurants/{count}")
    fun generateRestaurants(@PathVariable count: Int): List<Restaurant> {
        val themes = themeRepository.findAll().toList()

        val restaurants = if (themes.isNotEmpty()) {
            userFaker.generateRestaurants(count, themes)
        } else {
            listOf()
        }
        restaurantRepository.saveAll(restaurants)

        return restaurants
    }

    @GetMapping("/typePlats/{count}")
    fun generateTypePlats(@PathVariable count: Int): List<TypePlat> {
        val typePlats = userFaker.generateTypePlats(count)
        typePlatRepository.saveAll(typePlats)
        return typePlats
    }

    @GetMapping("/plats/{count}")
    fun generatePlats(@PathVariable count: Int): List<Plat> {
        val typePlats = typePlatRepository.findAll().toList()
        val restaurants = restaurantRepository.findAll().toList()
        if (typePlats.isNotEmpty() && restaurants.isNotEmpty()) {
            val plats = userFaker.generatePlats(count, typePlats, restaurants)
            platRepository.saveAll(plats)
            return plats
        } else {
            return listOf()
        }
    }

    @GetMapping("/evaluations/{count}")
    fun generateEvaluations(@PathVariable count: Int): List<Evaluation> {
        val restaurants = restaurantRepository.findAll().toList()
        val users = UserRepository.findAll().toList()

        if (restaurants.isEmpty() || users.isEmpty()) {
            return listOf()
        }

        val evaluations = userFaker.generateEvaluations(count, restaurants, users)
        evaluationRepository.saveAll(evaluations)

        return evaluations
    }

    @GetMapping("/guides/{count}")
    fun generateGuides(@PathVariable count: Int): List<Guide> {
        val users = UserRepository.findAll().toList()

        if (users.isEmpty()) {
            return listOf() // Retourne une liste vide si aucun utilisateur n'est disponible
        }

        val guides = userFaker.generateGuides(count, users)
        guideRepository.saveAll(guides)

        return guides
    }

    @GetMapping("/evalOfficielles/{count}")
    fun generateEvalOfficielles(@PathVariable count: Int): List<EvalOfficielle> {
        val guides = guideRepository.findAll().toList()
        val restaurants = restaurantRepository.findAll().toList()

        if (guides.isEmpty() || restaurants.isEmpty()) {
            return listOf()
        }

        val evalOfficielles = userFaker.generateEvalOfficielles(count, guides, restaurants)
        evalOfficielleRepository.saveAll(evalOfficielles)

        return evalOfficielles
    }


}