package com.filldor.library.book.repository;

import com.filldor.library.database.BookDatabase;
import com.filldor.library.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DatabaseBookRepository implements BookRepository {

    private final BookDatabase bookDatabase;

    public DatabaseBookRepository(BookDatabase bookDatabase) {
        this.bookDatabase = bookDatabase;
    }

    @Override
    public Book save(Book book) {
        return bookDatabase.save(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookDatabase.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDatabase.findAll();
    }
}
