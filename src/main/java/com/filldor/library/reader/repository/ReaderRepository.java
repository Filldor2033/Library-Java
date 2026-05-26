package com.filldor.library.reader.repository;

import com.filldor.library.entity.Reader;

import java.util.List;
import java.util.Optional;

public interface ReaderRepository {

    Reader save(Reader reader);

    Optional<Reader> findById(Long id);

    Optional<Reader> findByTicketNumber(String ticketNumber);

    List<Reader> findAll();
}
