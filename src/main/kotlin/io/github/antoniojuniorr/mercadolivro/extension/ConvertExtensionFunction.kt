package io.github.antoniojuniorr.mercadolivro.extension

import io.github.antoniojuniorr.mercadolivro.controller.request.PostBookRequest
import io.github.antoniojuniorr.mercadolivro.controller.request.PostCostumerRequest
import io.github.antoniojuniorr.mercadolivro.controller.request.PutBookRequest
import io.github.antoniojuniorr.mercadolivro.controller.request.PutCostumerRequest
import io.github.antoniojuniorr.mercadolivro.controller.response.BookResponse
import io.github.antoniojuniorr.mercadolivro.controller.response.CustomerResponse
import io.github.antoniojuniorr.mercadolivro.enums.BookStatus
import io.github.antoniojuniorr.mercadolivro.enums.CustomerStatus
import io.github.antoniojuniorr.mercadolivro.model.BookModel
import io.github.antoniojuniorr.mercadolivro.model.CustomerModel

fun PostCostumerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO)
}

fun PutCostumerRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel {
    return CustomerModel(id = previousValue.id, name = this.name, email = this.email, status = previousValue.status)
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer
    )
}

fun PutBookRequest.toBookModel(previousValue: BookModel): BookModel {
    return BookModel(
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        price = this.price ?: previousValue.price,
        status = previousValue.status,
        customer = previousValue.customer
    )
}

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer = this.customer,
        status = this.status
    )
}
