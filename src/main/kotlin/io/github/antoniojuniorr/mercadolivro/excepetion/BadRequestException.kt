package io.github.antoniojuniorr.mercadolivro.excepetion

class BadRequestException(override val message: String, val errorCode: String): Exception() {
}