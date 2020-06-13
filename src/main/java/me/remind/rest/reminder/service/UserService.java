package me.remind.rest.reminder.service;

import me.remind.rest.reminder.excetion.RepositoryException;
import me.remind.rest.reminder.pojo.User;
import me.remind.rest.reminder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
public class UserService { // no interface YAGNI-ed the use of interfaces and implementation for this tiny project
    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(final User user) {
        return userRepository.save(user);
    }

    public User findUser(final Long id) throws RepositoryException {
        return userRepository.findById(id)
                .orElseThrow(() -> new RepositoryException("No entity 'User' could not the founded for the id: " + id));
    }

    public List<User> findAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(toList());
    }

    public User updateUser(final User userParam) throws RepositoryException {
        User dbUser = findUser(userParam.getId());

        dbUser.setFirstName(userParam.getFirstName());  //For classes with 10s of properties BeanUtils.copyProperties should be used
        dbUser.setLastName(userParam.getLastName());
        dbUser.setPosition(userParam.getPosition());
        dbUser.setGithubUrl(userParam.getGithubUrl());
        return userRepository.save(dbUser);
    }

    public void deleteUser(final Long id) {
        userRepository.delete(new User(id));
    }
}
