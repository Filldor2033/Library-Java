package com.filldor.library.issue.repository;

import com.filldor.library.entity.Issue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryIssueRepository implements IssueRepository {

    private final ConcurrentMap<Long, Issue> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public Issue save(Issue issue) {
        if (issue.getId() == null) {
            issue.setId(idGenerator.incrementAndGet());
        }
        storage.put(issue.getId(), issue);
        return issue;
    }

    @Override
    public Optional<Issue> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Issue> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<Issue> findByReaderId(Long readerId) {
        return storage.values().stream()
                .filter(issue -> issue.getReaderId().equals(readerId))
                .toList();
    }

    @Override
    public List<Issue> findByBookId(Long bookId) {
        return storage.values().stream()
                .filter(issue -> issue.getBookId().equals(bookId))
                .toList();
    }

    @Override
    public Optional<Issue> findActiveByReaderId(Long readerId) {
        return storage.values().stream()
                .filter(issue -> issue.getReaderId().equals(readerId))
                .filter(issue -> issue.getActualReturnDate() == null)
                .findFirst();
    }

    @Override
    public Optional<Issue> findActiveByBookId(Long bookId) {
        return storage.values().stream()
                .filter(issue -> issue.getBookId().equals(bookId))
                .filter(issue -> issue.getActualReturnDate() == null)
                .findFirst();
    }
}
