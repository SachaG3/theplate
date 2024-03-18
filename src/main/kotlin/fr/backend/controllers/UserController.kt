package fr.backend.controllers

import fr.backend.exceptions.BadLoginException
import fr.backend.models.User
import fr.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/my/users")
class UserController {
    @Autowired
    lateinit var userRepository: UserRepository

    /**
     * Get all user with pagination
     */
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
}