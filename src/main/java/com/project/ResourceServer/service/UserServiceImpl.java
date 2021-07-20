package com.project.ResourceServer.service;

import com.project.ResourceServer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.ResourceServer.repos.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserInterface {


    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User addUser(User user) {

        user.setId(null);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {

        userRepository.delete(user);
    }

    @Override
    public User saveUser(Long id, User user) {

        User u = userRepository.findById(id).get();
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setNickname(user.getNickname());
        u.setEmail(user.getEmail());
        return userRepository.save(u);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
