package io.github.antoniojuniorr.mercadolivro.controller

import io.github.antoniojuniorr.mercadolivro.controller.request.PostCostumerRequest
import io.github.antoniojuniorr.mercadolivro.controller.request.PutCostumerRequest
import io.github.antoniojuniorr.mercadolivro.controller.response.CustomerResponse
import io.github.antoniojuniorr.mercadolivro.extension.toCustomerModel
import io.github.antoniojuniorr.mercadolivro.extension.toResponse
import io.github.antoniojuniorr.mercadolivro.service.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
class CustomerController(
    private val customerService: CustomerService
) {

    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerResponse> {
        return customerService.getAll(name).map { it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid customer: PostCostumerRequest) {
        customerService.create(customer.toCustomerModel())
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerResponse {
        return customerService.findById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody @Valid customer: PutCostumerRequest) {
        val customerSaved = customerService.findById(id)
        customerService.update(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleter(@PathVariable id: Int) {
        customerService.delete(id)
    }
}