package com.filldor.library.book.service;

import com.filldor.library.book.controller.CreateBookRequest;
import com.filldor.library.common.exception.BadRequestException;
import com.filldor.library.book.repository.BookRepository;
import com.filldor.library.common.exception.NotFoundException;
import com.filldor.library.entity.Book;
import com.filldor.library.entity.BookStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(CreateBookRequest request) {
        requireText(request.title(), "Book title must not be blank");
        requireText(request.author(), "Book author must not be blank");
        requireText(request.isbn(), "Book ISBN must not be blank");

        Book book = new Book();
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setIsbn(request.isbn());
        book.setGenre(request.genre());
        book.setPublicationYear(request.publicationYear());
        book.setStatus(BookStatus.AVAILABLE);
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findAll().stream()
                .filter(book -> book.getStatus() == BookStatus.AVAILABLE)
                .toList();
    }

    public List<Book> getIssuedBooks() {
        return bookRepository.findAll().stream()
                .filter(book -> book.getStatus() == BookStatus.ISSUED)
                .toList();
    }

    public List<Book> searchBooks(String title, String author, String isbn) {
        return bookRepository.findAll().stream()
                .filter(book -> matches(book.getTitle(), title))
                .filter(book -> matches(book.getAuthor(), author))
                .filter(book -> matches(book.getIsbn(), isbn))
                .toList();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with id " + id + " was not found"));
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    private boolean matches(String value, String filter) {
        if (filter == null || filter.isBlank()) {
            return true;
        }
        if (value == null) {
            return false;
        }
        return value.toLowerCase().contains(filter.toLowerCase());
    }

    private void requireText(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new BadRequestException(message);
        }
    }
}
