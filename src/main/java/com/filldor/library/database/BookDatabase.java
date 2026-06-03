package com.filldor.library.database;

import com.filldor.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDatabase extends JpaRepository<Book, Long> {
}
