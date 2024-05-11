package io.github.antoniojuniorr.mercadolivro.excepetion

import io.github.antoniojuniorr.mercadolivro.controller.response.ErrorResponse
import io.github.antoniojuniorr.mercadolivro.controller.response.FieldErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val erro = ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(erro, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val erro = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(erro, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleBadRequestException(
        ex: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val erro = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.message,
            "ML-0004",
            ex.bindingResult.fieldErrors.map { FieldErrorResponse(it.defaultMessage ?: "Invalid", it.field) }
        )
        return ResponseEntity(erro, HttpStatus.BAD_REQUEST)
    }
}