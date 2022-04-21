package com.project.ResourceServer.controller;

import com.project.ResourceServer.entity.Actor;
import com.project.ResourceServer.entity.Category;
import com.project.ResourceServer.entity.Film;
import com.project.ResourceServer.entity.User;
import com.project.ResourceServer.service.api.ActorService;
import com.project.ResourceServer.service.CategoryServiceImpl;
import com.project.ResourceServer.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.ResourceServer.service.FilmServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/films")
public class FilmsController {


    private final CategoryServiceImpl categoryService;
    private final UserServiceImpl userService;
    private final ActorService actorService;
    private final FilmServiceImpl filmService;

    @Autowired
    public FilmsController(FilmServiceImpl filmService, CategoryServiceImpl categoryService, UserServiceImpl userService, ActorService actorService) {
        this.filmService = filmService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.actorService = actorService;
    }

    @GetMapping("/")
    public String mainPage() {
        return "views/mainPage";
    }

    @GetMapping("/all")
    public String all(Model model) {
        List<Film> films = filmService.getAllFilms();
        model.addAttribute("films", films);
        return "views/allFilms";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("film", new Film());
        return "views/addFilm";
    }

    @PostMapping("/add")
    public String addFilm(@ModelAttribute Film film, Model model) {
        model.addAttribute("film", filmService.addFilm(film));
        return "views/getFilm";
    }

    @GetMapping("/{id}/edit")
    public String savePage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("film", filmService.getFilmById(id));
        return "views/save";
    }

    @PostMapping("/{id}")
    public String saveFilm(@PathVariable Long id, Film film, Model model) {
        model.addAttribute("film", filmService.saveFilm(id, film));
        return "views/getFilm";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFilm(@PathVariable Long id, Model model) {
        filmService.deleteFilm(id);
        model.addAttribute("films", filmService.getAllFilms());
        return "views/allFilms";
    }

    @GetMapping("/get/{id}")
    public String getFilm(@PathVariable Long id, Model model) {
        Film film = filmService.getFilmById(id);
        List<Actor> filmActors = film.actors;
        List<Category> filmCategories = film.categories;
        model.addAttribute("film", film);
        return "views/getFilm";
    }

    @GetMapping("/get")
    public String getFilmByName(@RequestParam(name = "name") String name, Model model) {
        Film film = filmService.getFilmByName(name);
        List<Actor> actors = film.getActors();
        model.addAttribute("actors", actors);
        model.addAttribute("film", film);
        return "views/getFilm";
    }


    @GetMapping("/add/actor/{id}")
    public String addActorPage(@PathVariable Long id, Model model) {
        model.addAttribute("film", filmService.getFilmById(id));
        return "views/newActor";
    }

    @PostMapping("/add/actor/{id}")
    public String addActor(@PathVariable Long id, @RequestParam(name = "name") String name, Model model) {
        Film film = filmService.getFilmById(id);
        Actor actor = actorService.getActorByName(name);
        film.AddActor(actor);
        model.addAttribute("film", filmService.saveFilm(film.getId(), film));
        return "views/getFilm";
    }

    @GetMapping("/add/category/{id}")
    public String addCategoryPage(@PathVariable Long id, Model model) {

        model.addAttribute("filmId" ,id);
        return "views/newCategory";
    }

    @PostMapping("/add/category/{id}")
    public String addCategory(@PathVariable Long id, @RequestParam(name = "name") String name, Model model) {
        Film film = filmService.getFilmById(id);
        Category category = categoryService.getCategory(name);
        film.AddCategory(category);
        model.addAttribute("film", filmService.saveFilm(film.getId(), film));
        return "views/getFilm";
    }
}
