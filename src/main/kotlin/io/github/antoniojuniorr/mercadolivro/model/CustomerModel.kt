package io.github.antoniojuniorr.mercadolivro.model

import io.github.antoniojuniorr.mercadolivro.enums.CustomerStatus
import jakarta.persistence.*

@Entity(name = "customer")
data class CustomerModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var email: String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus?
) {
    constructor() : this(null, "", "", null) {}
}