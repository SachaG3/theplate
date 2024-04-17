package fr.backend.DTO

class AuthDTO {
    @JvmRecord
    data class LoginRequest(val username: String, val password: String)

    @JvmRecord
    data class Response(val message: String, val token: String)
}