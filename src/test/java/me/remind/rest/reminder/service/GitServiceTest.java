package me.remind.rest.reminder.service;

import me.remind.rest.reminder.pojo.RepositoryDetails;
import me.remind.rest.reminder.pojo.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GitServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GitServiceTest.class);

    @Autowired
    public GitService gitService;

    @Autowired
    public UserService userService;

    @Test
    public void testTetRepositoryDetailsForUser() {
        User user = userService.createUser(new User("FACT", "Finder", "developer", "https://github.com/FACT-Finder"));

        try {
            List<RepositoryDetails> repositoryDetailsList = gitService.getRepositoryDetailsForUser(user.getId());
            for (RepositoryDetails repositoryDetails : repositoryDetailsList) {
                Assert.assertNotNull(repositoryDetails.getRepoName());
                Assert.assertFalse(repositoryDetails.getLanguages().isEmpty());
            }
        } catch (Exception e) {
            LOGGER.error("Error retrieving the git repositories {}", user.getGithubUrl());
        }
    }
}