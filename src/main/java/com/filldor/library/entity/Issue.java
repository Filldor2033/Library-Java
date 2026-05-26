package com.filldor.library.entity;

import java.time.LocalDate;

public class Issue {

    private Long id;
    private Long bookId;
    private Long readerId;
    private LocalDate issueDate;
    private LocalDate plannedReturnDate;
    private LocalDate actualReturnDate;

    public Issue() {
    }

    public Issue(Long id, Long bookId, Long readerId, LocalDate issueDate,
                 LocalDate plannedReturnDate, LocalDate actualReturnDate) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.issueDate = issueDate;
        this.plannedReturnDate = plannedReturnDate;
        this.actualReturnDate = actualReturnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getPlannedReturnDate() {
        return plannedReturnDate;
    }

    public void setPlannedReturnDate(LocalDate plannedReturnDate) {
        this.plannedReturnDate = plannedReturnDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }
}
