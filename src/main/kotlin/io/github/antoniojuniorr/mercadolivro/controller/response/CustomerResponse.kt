package io.github.antoniojuniorr.mercadolivro.controller.response

import io.github.antoniojuniorr.mercadolivro.enums.CustomerStatus

data class CustomerResponse(
    var id: Int? = null,
    var name: String,
    var email: String,
    var status: CustomerStatus?
)
