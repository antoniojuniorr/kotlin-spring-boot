package io.github.antoniojuniorr.mercadolivro.repository

import io.github.antoniojuniorr.mercadolivro.enums.BookStatus
import io.github.antoniojuniorr.mercadolivro.model.BookModel
import io.github.antoniojuniorr.mercadolivro.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<BookModel, Int> {
    fun findByStatus(status: BookStatus, pageable: Pageable): Page<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>
}