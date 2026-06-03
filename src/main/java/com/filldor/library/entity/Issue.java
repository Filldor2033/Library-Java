package com.filldor.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "issue")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;

    private LocalDate issueDate;
    private LocalDate plannedReturnDate;
    private LocalDate actualReturnDate;

    public Issue() {
    }

    public Issue(Long id, Long bookId, Long readerId, LocalDate issueDate,
                 LocalDate plannedReturnDate, LocalDate actualReturnDate) {
        this.id = id;
        setBookId(bookId);
        setReaderId(readerId);
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
        return book == null ? null : book.getId();
    }

    public void setBookId(Long bookId) {
        if (bookId == null) {
            this.book = null;
            return;
        }
        Book linkedBook = new Book();
        linkedBook.setId(bookId);
        this.book = linkedBook;
    }

    public Long getReaderId() {
        return reader == null ? null : reader.getId();
    }

    public void setReaderId(Long readerId) {
        if (readerId == null) {
            this.reader = null;
            return;
        }
        Reader linkedReader = new Reader();
        linkedReader.setId(readerId);
        this.reader = linkedReader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
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
