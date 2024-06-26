package io.github.antoniojuniorr.mercadolivro.service

import io.github.antoniojuniorr.mercadolivro.enums.BookStatus
import io.github.antoniojuniorr.mercadolivro.excepetion.NotFoundException
import io.github.antoniojuniorr.mercadolivro.model.BookModel
import io.github.antoniojuniorr.mercadolivro.model.CustomerModel
import io.github.antoniojuniorr.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {

    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    fun findAll(pageable: Pageable): Page<BookModel> {
        return bookRepository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow {
            NotFoundException("Book [${id}] not exists", "ML-0001")
        }
    }

    fun deleter(id: Int) {
        val book = findById(id)
        book.status = BookStatus.CANCELADO
        update(book)
    }

    fun update(book: BookModel) {
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        for (book in books) {
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }
}
