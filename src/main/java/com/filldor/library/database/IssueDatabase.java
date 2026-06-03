package com.filldor.library.database;

import com.filldor.library.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueDatabase extends JpaRepository<Issue, Long> {

    List<Issue> findByReader_Id(Long readerId);

    List<Issue> findByBook_Id(Long bookId);

    Optional<Issue> findFirstByReader_IdAndActualReturnDateIsNull(Long readerId);

    Optional<Issue> findFirstByBook_IdAndActualReturnDateIsNull(Long bookId);
}
