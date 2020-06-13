package me.remind.rest.reminder.service;

import me.remind.rest.reminder.pojo.User;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);


    @Autowired
    public UserService userService;

    @BeforeEach
    public void init() {
        userService.createUser(new User("firstName1", "lastName1", "developer", "http://hithub.com/firstName1"));
        userService.createUser(new User("firstName2", "lastName2", "developer", "http://hithub.com/firstName2"));
        userService.createUser(new User("firstName3", "lastName3", "developer", "http://hithub.com/firstName3"));
        userService.createUser(new User("firstName4", "lastName4", "developer", "http://hithub.com/firstName4"));
    }

    @Test
    public void createUser() {
        User user = userService.createUser(new User("firstName5", "lastName5", "developer", "http://hithub.com/firstName5"));
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void findUser() {
        try {
            userService.findUser(1L);
            Assert.assertTrue(true);
        } catch (Exception e) {
            LOGGER.error("Error retrieving the user : {}", 1L);
            Assert.fail();
        }
    }

    @Test
    public void findAllUsers() {
        List<User> users = userService.findAllUsers();
        org.junit.Assert.assertEquals(4, userService.findAllUsers().size());
        for (User user : users) {
            Assert.assertNotNull(user.getId());
        }
    }

    @Test
    public void deleteUser() {
        User user = userService.createUser(new User("firstName5", "lastName5", "developer", "http://hithub.com/firstName5"));
        userService.deleteUser(user.getId());
        try {
            userService.findUser(user.getId());
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @AfterEach
    public void destroy() {
        for (User user : userService.findAllUsers()) {
            userService.deleteUser(user.getId());
        }
    }
}