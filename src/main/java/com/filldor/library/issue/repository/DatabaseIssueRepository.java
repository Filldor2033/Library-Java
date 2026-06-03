package com.filldor.library.issue.repository;

import com.filldor.library.database.IssueDatabase;
import com.filldor.library.entity.Issue;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DatabaseIssueRepository implements IssueRepository {

    private final IssueDatabase issueDatabase;

    public DatabaseIssueRepository(IssueDatabase issueDatabase) {
        this.issueDatabase = issueDatabase;
    }

    @Override
    public Issue save(Issue issue) {
        return issueDatabase.save(issue);
    }

    @Override
    public Optional<Issue> findById(Long id) {
        return issueDatabase.findById(id);
    }

    @Override
    public List<Issue> findAll() {
        return issueDatabase.findAll();
    }

    @Override
    public List<Issue> findByReaderId(Long readerId) {
        return issueDatabase.findByReader_Id(readerId);
    }

    @Override
    public List<Issue> findByBookId(Long bookId) {
        return issueDatabase.findByBook_Id(bookId);
    }

    @Override
    public Optional<Issue> findActiveByReaderId(Long readerId) {
        return issueDatabase.findFirstByReader_IdAndActualReturnDateIsNull(readerId);
    }

    @Override
    public Optional<Issue> findActiveByBookId(Long bookId) {
        return issueDatabase.findFirstByBook_IdAndActualReturnDateIsNull(bookId);
    }
}
