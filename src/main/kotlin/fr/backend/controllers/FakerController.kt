package fr.backend.controllers

import fr.backend.faker.DataFaker
import fr.backend.repository.RoleRepository
import fr.backend.repository.UserRepository
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

    @GetMapping("/roles")
    fun generateRoles() {
        val roles = userFaker.generateRole()
        RoleRepository.saveAll(roles)
    }

    @GetMapping("/users/{count}")
    fun generate(@PathVariable count: Int) {
        val users = userFaker.generateUser(count)
        users.forEach {
            it.role = RoleRepository.findByName("USER")!!
        }
        UserRepository.saveAll(users)
    }
}