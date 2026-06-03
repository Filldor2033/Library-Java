package com.filldor.library.database;

import com.filldor.library.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReaderDatabase extends JpaRepository<Reader, Long> {

    Optional<Reader> findByTicketNumberIgnoreCase(String ticketNumber);
}
