package com.filldor.library.reader.repository;

import com.filldor.library.database.ReaderDatabase;
import com.filldor.library.entity.Reader;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DatabaseReaderRepository implements ReaderRepository {

    private final ReaderDatabase readerDatabase;

    public DatabaseReaderRepository(ReaderDatabase readerDatabase) {
        this.readerDatabase = readerDatabase;
    }

    @Override
    public Reader save(Reader reader) {
        return readerDatabase.save(reader);
    }

    @Override
    public Optional<Reader> findById(Long id) {
        return readerDatabase.findById(id);
    }

    @Override
    public Optional<Reader> findByTicketNumber(String ticketNumber) {
        return readerDatabase.findByTicketNumberIgnoreCase(ticketNumber);
    }

    @Override
    public List<Reader> findAll() {
        return readerDatabase.findAll();
    }
}
