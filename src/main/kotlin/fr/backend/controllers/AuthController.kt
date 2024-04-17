package fr.backend.controllers

import fr.backend.DTO.AuthDTO
import fr.backend.exceptions.BadLoginException
import fr.backend.models.User
import fr.backend.repository.UserRepository
import fr.backend.security.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("api/auth")
@Validated
class AuthController {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var authService: AuthService

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @PostMapping("/login")
    @Throws(IllegalAccessException::class)
    fun login(@RequestBody userLogin: AuthDTO.LoginRequest): ResponseEntity<*> {
        val authentication: Authentication =
            authenticationManager
                .authenticate(
                    UsernamePasswordAuthenticationToken(
                        userLogin.username,
                        userLogin.password
                    )
                )
        SecurityContextHolder.getContext().authentication = authentication
        val token = authService.generateToken(authentication)
        val response: AuthDTO.Response = AuthDTO.Response("User logged in successfully", token)
        println("ok")
        return ResponseEntity.ok<Any>(response)
    }


    @GetMapping
    fun getAllUsers(
        @RequestParam(required = false) page: Int = 0,
        @RequestParam(required = false) pageSize: Int = 10
    ): Page<User> {
        return userRepository.findAll(PageRequest.of(page, pageSize))
    }

    @PostMapping
    fun addUser(@RequestBody user: User): ResponseEntity<User> {
        try {
            userRepository.save(user)
        } catch (e: DataIntegrityViolationException) {
            throw BadLoginException()
        }
        return ResponseEntity.created(URI.create("/users/${user.id}")).body(user)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: UUID): ResponseEntity<User> {
        val user = userRepository.findById(id)
        return if (user.isPresent) {
            ResponseEntity.ok(user.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun partiallyUpdateUser(@PathVariable id: UUID, @RequestBody user: User): ResponseEntity<User> {
        if (userRepository.existsById(id)) {
            val currentUser = userRepository.findById(id).get()
            if (user.login != null) {
                currentUser.login = user.login
            }
            if (user.password != null) {
                currentUser.password = user.password
            }
            userRepository.save(currentUser)
            return ResponseEntity.ok(currentUser)
        }
        return ResponseEntity.notFound().build()
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: UUID, @RequestBody user: User): ResponseEntity<User> {
        if (userRepository.existsById(id)) {
            userRepository.save(user)
            return ResponseEntity.ok(user)
        }
        return ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: UUID): ResponseEntity<Unit> {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            return ResponseEntity.noContent().build()
        }
        return ResponseEntity.notFound().build()
    }

    @PostMapping("/login2")
    fun login(@RequestBody loginDetails: LoginRequest): ResponseEntity<String> {
        println(loginDetails)
        return try {
            val user = userRepository.findByEmail(loginDetails.email)
            println(user)
            if (user != null && passwordEncoder.matches(loginDetails.password, user.password)) {
                println("ok")
                return ResponseEntity.ok().body("{\"success\": true}")
            } else {
                ResponseEntity.badRequest().body("Erreur de connexion : identifiants invalides.")
            }
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body("Une erreur serveur est survenue.")
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody registrationDetails: RegistrationRequest): ResponseEntity<Any> {
        println("Inscription de l'utilisateur: $registrationDetails")
        return try {
            val existingUser = userRepository.findByEmail(registrationDetails.email)
            if (existingUser != null) {
                ResponseEntity.badRequest().body("Erreur d'inscription : l'email est déjà utilisé.")
            } else {
                val hashedPassword = passwordEncoder.encode(registrationDetails.password)
                val newUser = User().apply {
                    login = registrationDetails.name
                    email = registrationDetails.email
                    password = hashedPassword
                    // Configurer les champs supplémentaires si nécessaire
                }
                userRepository.save(newUser)
                ResponseEntity.ok(mapOf("success" to true, "message" to "Inscription réussie"))
            }
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body("Une erreur serveur est survenue lors de l'inscription.")
        }
    }


    data class RegistrationRequest(
        val name: String,
        val email: String,
        val password: String
    )


    data class LoginRequest(
        val email: String,
        val password: String,
    )
}