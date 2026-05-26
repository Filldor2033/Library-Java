package com.filldor.library.issue.controller;

import java.time.LocalDate;

public record CreateIssueRequest(
        Long bookId,
        Long readerId,
        LocalDate plannedReturnDate
) {
}
