package com.project.ResourceServer.controller;

import com.project.ResourceServer.entity.Film;
import com.project.ResourceServer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.ResourceServer.service.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    UserServiceImpl  userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public User add(@RequestBody User user)
    {
        return userService.addUser(user);
    }

    @PostMapping("/save")
    public User save(@RequestBody User user)
    {
        return userService.saveUser(user.getId(),user);
    }

    @DeleteMapping("/delete")
    public void delete(User user)
    {
        userService.deleteUser(user);
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable Long id)
    {
        return userService.getUser(id);
    }

    @GetMapping("/films/{id}")
    public String  getFilms(@PathVariable Long id, Model model)
    {
        User user = userService.getUser(id);
        List<Film> films = user.getFilms();
        model.addAttribute("films",films);
        return "views/favFilms";
    }
}
