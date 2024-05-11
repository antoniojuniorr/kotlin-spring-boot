package io.github.antoniojuniorr.mercadolivro.model

import io.github.antoniojuniorr.mercadolivro.enums.BookStatus
import io.github.antoniojuniorr.mercadolivro.excepetion.BadRequestException
import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "book")
data class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null

) {
    constructor() : this(null, "", BigDecimal.ZERO, null) {

    }

    constructor(id: Int? = null, name: String, price: BigDecimal, customer: CustomerModel? = null, status: BookStatus?)
            : this(id, name, price, customer) {
        this.status = status
    }

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if (field == BookStatus.CANCELADO || field == BookStatus.DELETADO) {
                throw BadRequestException("Cannot update book with status [${field}]", "ML-0003")
            }
            field = value
        }
}