package com.project.ResourceServer.controller;

import com.project.ResourceServer.entity.Actor;
import com.project.ResourceServer.entity.Category;
import com.project.ResourceServer.entity.Film;
import com.project.ResourceServer.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.ResourceServer.service.CategoryServiceImpl;

import java.awt.*;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/category")
public class CategoryController {

    CategoryServiceImpl categoryService;
    FilmService filmService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService, FilmService filmService) {

        this.categoryService = categoryService;
        this.filmService = filmService;
    }

    @GetMapping("/all")
    public String all(Model model)
    {
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "views/allCategories";
    }

    @PostMapping("/all")
    public String add(@RequestParam String name, @RequestParam Long id, Model model)
    {

        Category category = new Category(id,name);
        categoryService.addCategory(category);
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("actors", categories);
        return "views/allCategories";
    }

    @PostMapping("/add")
    public Category addCategory(@RequestBody Category category)
    {
        return categoryService.addCategory(category);
    }

    @PatchMapping("/save/{id}")
    public String saveCategory(@RequestParam(name="name")String name,
                                 @PathVariable Long id)
    {
        Category category = new Category(id,name);
        categoryService.saveCategory(category.getId(),category);
        return "views/allCategories";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id)
    {
        Category category = categoryService.getCategoryById(id);
        categoryService.deleteFilmsFromCategory(category);
        List<Film> films = filmService.getAllFilms();
        for (Film film : films) {
            if(film.getCategories().contains(category))
            {
                film.getCategories().remove(category);
                filmService.saveFilm(film.getId(),film);
            }
        }
        categoryService.deleteCategory(category);
        return "views/allCategories";
    }

    @GetMapping("/get/{id}")
    public String getCategory(@PathVariable Long id, Model model)
    {
         Category category = categoryService.getCategoryById(id);
         model.addAttribute("category", category);
         return "views/get";
    }

    @GetMapping("films/{id}")
    public String getFilms(@PathVariable Long id, Model model)
    {
        Category category = categoryService.getCategoryById(id);
        List<Film> films = category.getFilms();
        model.addAttribute("films", films);
        model.addAttribute("category", category);
        return "views/categoryFilms";
    }

    @GetMapping("/get")
    public String getByName(@RequestParam(name = "name") String name, Model model)
    {
        Category category = categoryService.getCategory(name);
        model.addAttribute("category",category);
        return "views/get";
    }



}
