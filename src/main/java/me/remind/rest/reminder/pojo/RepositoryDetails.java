package me.remind.rest.reminder.pojo;

import java.util.Set;

public class RepositoryDetails {
    private String repoName;
    private Set<String> languages;

    public RepositoryDetails(String repoName, Set<String> languages) {
        this.repoName = repoName;
        this.languages = languages;
    }

    public RepositoryDetails() {
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public Set<String> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<String> languages) {
        this.languages = languages;
    }
}
