package com.filldor.library.issue.controller;

import com.filldor.library.entity.Issue;
import com.filldor.library.issue.service.IssueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping
    public Issue createIssue(@RequestBody CreateIssueRequest request) {
        return issueService.createIssue(request);
    }

    @PostMapping("/{issueId}/return")
    public Issue returnBook(@PathVariable Long issueId) {
        return issueService.returnBook(issueId);
    }

    @GetMapping
    public List<Issue> getAllIssues() {
        return issueService.getAllIssues();
    }

    @GetMapping("/current")
    public List<Issue> getCurrentIssues() {
        return issueService.getCurrentIssues();
    }

    @GetMapping("/overdue")
    public List<Issue> getOverdueIssues() {
        return issueService.getOverdueIssues();
    }

    @GetMapping("/reader/{readerId}")
    public List<Issue> getIssueHistoryByReader(@PathVariable Long readerId) {
        return issueService.getIssueHistoryByReader(readerId);
    }

    @GetMapping("/book/{bookId}")
    public List<Issue> getIssueHistoryByBook(@PathVariable Long bookId) {
        return issueService.getIssueHistoryByBook(bookId);
    }
}
