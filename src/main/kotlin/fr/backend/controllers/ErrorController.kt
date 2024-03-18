package fr.backend.controllers

import fr.backend.exceptions.AppMessage
import fr.backend.exceptions.BadLoginException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorController {
    @ExceptionHandler(BadLoginException::class)
    fun handleBadLoginException(badLoginException: BadLoginException): ResponseEntity<AppMessage> {
        return ResponseEntity.badRequest().body(AppMessage(badLoginException.message, 400))
    }
}