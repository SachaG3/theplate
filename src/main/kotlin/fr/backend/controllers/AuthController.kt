package fr.backend.controllers

import fr.backend.DTO.AuthDTO
import fr.backend.security.AuthService
import fr.backend.security.AuthUser

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/auth")
@Validated
class AuthController {

    @Autowired
    lateinit var authService: AuthService

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @PostMapping("/login")
    @Throws(IllegalAccessException::class)
    fun login(@RequestBody userLogin: AuthDTO.LoginRequest): ResponseEntity<*> {
        println(userLogin)
        val authentication: Authentication =
            authenticationManager
                .authenticate(
                    UsernamePasswordAuthenticationToken(
                        userLogin.username,
                        userLogin.password
                    )
                )
        SecurityContextHolder.getContext().authentication = authentication
        val userDetails = authentication.principal as AuthUser
        log.info("Token requested for user :{}", authentication.authorities)
        val token = authService.generateToken(authentication)
        
        val response: AuthDTO.Response = AuthDTO.Response("User logged in successfully", token)
        return ResponseEntity.ok<Any>(response)
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(AuthController::class.java)
    }
}
