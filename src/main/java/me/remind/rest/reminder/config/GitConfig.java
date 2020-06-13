package me.remind.rest.reminder.config;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GitConfig {
    @Value("${git.token}")
    private String gitToken;

    public String getGitToken() {
        return gitToken;
    }

    @Bean
    public GitHub getGitConnection() {
        try {
            return new GitHubBuilder().withOAuthToken(gitToken).build();
        } catch (IOException io) {
            throw new RuntimeException("Not able to connect to git");
        }
    }
}
