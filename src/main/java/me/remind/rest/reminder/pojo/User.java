package me.remind.rest.reminder.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @JsonProperty("firstname")
    private String firstName;

    @NotEmpty
    @JsonProperty("surname")
    private String lastName;

    @NotEmpty
    private String position; // ideally this should be an enum but accepting any string as of now;

    @NotEmpty
    @URL
    private String githubUrl; // Not performing validation for this as of now


    public User() {
        // empty constructor
    }

    public User(@NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty String position, @NotEmpty @URL String githubUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.githubUrl = githubUrl;
    }

    public User(Long id, @NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty String position, @NotEmpty @URL String githubUrl) {
        this(firstName, lastName, position, githubUrl);
        this.id = id;
    }

    public User(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }
}
