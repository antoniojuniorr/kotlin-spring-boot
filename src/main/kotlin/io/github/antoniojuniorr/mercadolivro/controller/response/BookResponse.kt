package io.github.antoniojuniorr.mercadolivro.controller.response

import io.github.antoniojuniorr.mercadolivro.enums.BookStatus
import io.github.antoniojuniorr.mercadolivro.model.CustomerModel
import java.math.BigDecimal

data class BookResponse(
    var id: Int? = null,
    var name: String,
    var price: BigDecimal,
    var customer: CustomerModel? = null,
    var status: BookStatus? = null
)