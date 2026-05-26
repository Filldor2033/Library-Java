package com.filldor.library.issue.repository;

import com.filldor.library.entity.Issue;

import java.util.List;
import java.util.Optional;

public interface IssueRepository {

    Issue save(Issue issue);

    Optional<Issue> findById(Long id);

    List<Issue> findAll();

    List<Issue> findByReaderId(Long readerId);

    List<Issue> findByBookId(Long bookId);

    Optional<Issue> findActiveByReaderId(Long readerId);

    Optional<Issue> findActiveByBookId(Long bookId);
}
