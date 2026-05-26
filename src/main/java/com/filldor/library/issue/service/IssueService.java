package com.filldor.library.issue.service;

import com.filldor.library.book.service.BookService;
import com.filldor.library.common.exception.BadRequestException;
import com.filldor.library.common.exception.NotFoundException;
import com.filldor.library.entity.Book;
import com.filldor.library.entity.BookStatus;
import com.filldor.library.entity.Issue;
import com.filldor.library.issue.controller.CreateIssueRequest;
import com.filldor.library.issue.repository.IssueRepository;
import com.filldor.library.reader.service.ReaderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IssueService {

    private static final int DEFAULT_ISSUE_DAYS = 14;

    private final IssueRepository issueRepository;
    private final BookService bookService;
    private final ReaderService readerService;

    public IssueService(IssueRepository issueRepository, BookService bookService, ReaderService readerService) {
        this.issueRepository = issueRepository;
        this.bookService = bookService;
        this.readerService = readerService;
    }

    public Issue createIssue(CreateIssueRequest request) {
        if (request.bookId() == null || request.readerId() == null) {
            throw new BadRequestException("bookId and readerId must be provided");
        }

        Book book = bookService.getBookById(request.bookId());
        readerService.getReaderById(request.readerId());

        if (book.getStatus() == BookStatus.ISSUED) {
            throw new BadRequestException("Book with id " + request.bookId() + " is already issued");
        }

        ensureReaderHasNoOverdueIssues(request.readerId());
        validatePlannedReturnDate(request.plannedReturnDate());

        Issue issue = new Issue();
        issue.setBookId(request.bookId());
        issue.setReaderId(request.readerId());
        issue.setIssueDate(LocalDate.now());
        issue.setPlannedReturnDate(resolvePlannedReturnDate(request.plannedReturnDate()));

        Issue savedIssue = issueRepository.save(issue);
        book.setStatus(BookStatus.ISSUED);
        bookService.save(book);
        return savedIssue;
    }

    public Issue returnBook(Long issueId) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new NotFoundException("Issue with id " + issueId + " was not found"));

        if (issue.getActualReturnDate() != null) {
            throw new BadRequestException("Issue with id " + issueId + " is already closed");
        }

        Book book = bookService.getBookById(issue.getBookId());
        issue.setActualReturnDate(LocalDate.now());
        Issue savedIssue = issueRepository.save(issue);

        book.setStatus(BookStatus.AVAILABLE);
        bookService.save(book);
        return savedIssue;
    }

    public List<Issue> getCurrentIssues() {
        return issueRepository.findAll().stream()
                .filter(issue -> issue.getActualReturnDate() == null)
                .toList();
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public List<Issue> getIssueHistoryByReader(Long readerId) {
        readerService.getReaderById(readerId);
        return issueRepository.findByReaderId(readerId);
    }

    public List<Issue> getIssueHistoryByBook(Long bookId) {
        bookService.getBookById(bookId);
        return issueRepository.findByBookId(bookId);
    }

    public List<Issue> getOverdueIssues() {
        LocalDate today = LocalDate.now();
        return issueRepository.findAll().stream()
                .filter(issue -> issue.getActualReturnDate() == null)
                .filter(issue -> issue.getPlannedReturnDate().isBefore(today))
                .toList();
    }

    private LocalDate resolvePlannedReturnDate(LocalDate plannedReturnDate) {
        if (plannedReturnDate != null) {
            return plannedReturnDate;
        }
        return LocalDate.now().plusDays(DEFAULT_ISSUE_DAYS);
    }

    private void validatePlannedReturnDate(LocalDate plannedReturnDate) {
        if (plannedReturnDate != null && plannedReturnDate.isBefore(LocalDate.now())) {
            throw new BadRequestException("plannedReturnDate must not be before today");
        }
    }

    private void ensureReaderHasNoOverdueIssues(Long readerId) {
        issueRepository.findAll().stream()
                .filter(issue -> issue.getReaderId().equals(readerId))
                .filter(issue -> issue.getActualReturnDate() == null)
                .filter(issue -> issue.getPlannedReturnDate().isBefore(LocalDate.now()))
                .findFirst()
                .ifPresent(issue -> {
                    throw new BadRequestException(
                            "Reader with id " + readerId + " has overdue issue with id " + issue.getId()
                    );
                });
    }
}
