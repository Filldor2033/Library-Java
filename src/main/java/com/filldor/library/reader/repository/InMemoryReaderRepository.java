package com.filldor.library.reader.repository;

import com.filldor.library.entity.Reader;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryReaderRepository implements ReaderRepository {

    private final ConcurrentMap<Long, Reader> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public Reader save(Reader reader) {
        if (reader.getId() == null) {
            reader.setId(idGenerator.incrementAndGet());
        }
        storage.put(reader.getId(), reader);
        return reader;
    }

    @Override
    public Optional<Reader> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Optional<Reader> findByTicketNumber(String ticketNumber) {
        return storage.values().stream()
                .filter(reader -> reader.getTicketNumber() != null)
                .filter(reader -> reader.getTicketNumber().equalsIgnoreCase(ticketNumber))
                .findFirst();
    }

    @Override
    public List<Reader> findAll() {
        return new ArrayList<>(storage.values());
    }
}
