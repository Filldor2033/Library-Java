package com.filldor.library.reader.controller;

import java.time.LocalDate;

public record CreateReaderRequest(
        String fullName,
        LocalDate birthDate,
        String ticketNumber,
        String phone,
        String email
) {
}
