package me.remind.rest.reminder.controller;

import me.remind.rest.reminder.excetion.RepositoryException;
import me.remind.rest.reminder.pojo.RepositoryDetails;
import me.remind.rest.reminder.service.GitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/users")
public class GitController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GitController.class);
    private final GitService gitService;

    @Autowired
    public GitController(GitService gitService) {
        this.gitService = gitService;
    }

    @GetMapping("/{id}/repositories")
    public ResponseEntity<List<RepositoryDetails>> getRepositoryDetailsForUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(gitService.getRepositoryDetailsForUser(id));
        } catch (RepositoryException | IOException re) {
            LOGGER.error("Error occurred retrieving the repository details", re);
            return ResponseEntity.notFound().build();
        }
    }
}
