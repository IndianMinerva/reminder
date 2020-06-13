package me.remind.rest.reminder.service;

import me.remind.rest.reminder.controller.GitController;
import me.remind.rest.reminder.excetion.RepositoryException;
import me.remind.rest.reminder.pojo.RepositoryDetails;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class GitService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GitController.class);
    private final static String SLASH = "/";
    private final static String EMPTY = "";

    private final GitHub gitConnection;
    private final UserService userService;

    @Autowired
    public GitService(final GitHub gitConnection, final UserService userService) {
        this.gitConnection = gitConnection;
        this.userService = userService;
    }

    public List<RepositoryDetails> getRepositoryDetailsForUser(Long id) throws IOException, RepositoryException {
        final List<RepositoryDetails> repositoryDetailsList = new LinkedList<>();
        final String githubUrl = userService.findUser(id).getGithubUrl();
        Map<String, GHRepository> repositories = gitConnection.getUser(extractUserIdFromGithubUrl(githubUrl)).getRepositories();
        for (Map.Entry<String, GHRepository> repositoryEntry : repositories.entrySet()) {
            try {
                repositoryDetailsList.add(new RepositoryDetails(repositoryEntry.getKey(), repositoryEntry.getValue().listLanguages().keySet()));
            } catch (IOException ioe) {
                LOGGER.error("Error retrieving the languages", ioe);
            }
        }
        return repositoryDetailsList;
    }

    private String extractUserIdFromGithubUrl(String githubUrl) throws MalformedURLException {
        URL url = new URL(githubUrl);
        return url.getPath().replaceAll(SLASH, EMPTY);
    }
}
