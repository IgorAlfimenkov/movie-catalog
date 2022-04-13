package com.project.ResourceServer.service.api;

import com.project.ResourceServer.entity.User;

import java.util.List;

public interface UserInterface {

    User getUser(Long id);
    User addUser(User user);
    void deleteUser(User user);
    User saveUser(Long id, User user);
    List<User> getAllUsers();
}
