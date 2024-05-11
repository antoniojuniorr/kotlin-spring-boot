package io.github.antoniojuniorr.mercadolivro.service

import io.github.antoniojuniorr.mercadolivro.enums.CustomerStatus
import io.github.antoniojuniorr.mercadolivro.excepetion.NotFoundException
import io.github.antoniojuniorr.mercadolivro.model.CustomerModel
import io.github.antoniojuniorr.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val bookService: BookService
) {

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContainingIgnoreCase(name)
        }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow {
            NotFoundException("Customer [${id}] not exists", "ML-0002")
        }
    }

    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }
}