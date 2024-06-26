package io.github.antoniojuniorr.mercadolivro.repository

import io.github.antoniojuniorr.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerModel, Int> {
    fun findByNameContainingIgnoreCase(name: String): List<CustomerModel>
}