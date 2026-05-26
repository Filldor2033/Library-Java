package com.filldor.library.reader.service;

import com.filldor.library.common.exception.BadRequestException;
import com.filldor.library.common.exception.NotFoundException;
import com.filldor.library.entity.Reader;
import com.filldor.library.reader.controller.CreateReaderRequest;
import com.filldor.library.reader.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public Reader createReader(CreateReaderRequest request) {
        requireText(request.fullName(), "Reader full name must not be blank");
        requireText(request.ticketNumber(), "Reader ticket number must not be blank");

        if (readerRepository.findByTicketNumber(request.ticketNumber()).isPresent()) {
            throw new BadRequestException("Reader with ticket number " + request.ticketNumber() + " already exists");
        }

        Reader reader = new Reader();
        reader.setFullName(request.fullName());
        reader.setBirthDate(request.birthDate());
        reader.setTicketNumber(request.ticketNumber());
        reader.setPhone(request.phone());
        reader.setEmail(request.email());
        return readerRepository.save(reader);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader getReaderById(Long id) {
        return readerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reader with id " + id + " was not found"));
    }

    private void requireText(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new BadRequestException(message);
        }
    }
}
