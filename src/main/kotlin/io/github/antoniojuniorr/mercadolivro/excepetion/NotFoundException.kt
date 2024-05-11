package io.github.antoniojuniorr.mercadolivro.excepetion

class NotFoundException(override val message: String, val errorCode: String): Exception() {
}