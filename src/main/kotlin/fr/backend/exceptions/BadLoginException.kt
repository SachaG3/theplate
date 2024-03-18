package fr.backend.exceptions

class BadLoginException : RuntimeException() {
    override val message: String
        get() = "Ce login est déjà utilisé."
}