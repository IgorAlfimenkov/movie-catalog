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
    private final  FilmServiceImpl filmService;

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
    public String addPage() {
        return "views/addFilm";
    }

    @PostMapping("/add")
    public String addFilm(@RequestParam(name = "id") Long id,
                          @RequestParam(name = "filmName", required = false) String filmName,
                          @RequestParam(name = "year") int year,
                          @RequestParam(name = "duration") int duration,
                          @RequestParam(name = "rating") float rating,
                          @RequestParam(name = "description") String description,
                          @RequestParam(name = "poster") String poster,
                          @RequestParam(name = "trailer") String trailer,
                          @RequestParam(name = "companyname") String company,
                          @RequestParam(name = "category") String category) {
        Film film = new Film(id, filmName, description, poster, trailer, year, rating, duration, company);
        Category category1 = categoryService.getCategory(category);
        film.setCategories(category1);
        filmService.addFilm(film);
        return "views/allFilms";
    }

    @GetMapping("/{id}/edit")
    public String savePage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("film", filmService.getFilmById(id));
        return "views/save";
    }

    @PostMapping("/{id}")
    public String saveFilm(@PathVariable Long id, Film film) {
        filmService.saveFilm(id, film);
        return "views/allFilms";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFilm(@PathVariable Long id) {
        Film film = filmService.getFilmById(id);
        filmService.deleteActorsFromFilm(film);
        filmService.deleteCategoriesFromFilm(film);
        filmService.deleteUsersFromFilm(film);
        filmService.saveFilm(film.getId(), film);
        List<Actor> actors = actorService.getAllActors();
        for (Actor actor : actors) {
            if (actor.getFilms().contains(film)) {

                actor.getFilms().remove(film);
                actorService.saveActor(actor.getId(), actor);
            }
        }
        List<Category> categories = categoryService.getCategories();
        for (Category category : categories) {
            if (category.getFilms().contains(film)) {

                category.getFilms().remove(film);
                categoryService.saveCategory(category.getId(), category);
            }
        }
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            if (user.getFilms().contains(film)) {

                user.getFilms().remove(film);
                userService.saveUser(user.getId(), user);
            }
        }

        filmService.deleteFilm(film);
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

    @GetMapping("/actors/{id}")
    public String getActors(@PathVariable Long id, Model model) {
        Film film = filmService.getFilmById(id);
        List<Actor> actors = film.getActors();
        model.addAttribute("actors", actors);
        model.addAttribute("film", film);
        return "views/filmActors";
    }


    @GetMapping("/add/actor/{id}")
    public String addActorPage(@PathVariable Long id, Model model) {
        model.addAttribute("filmId", id);
        return "views/newActor";
    }

    @PostMapping("/add/actor/{id}")
    public String addActor(@PathVariable Long id, @RequestParam(name = "name") String name, Model model) {
        Film film = filmService.getFilmById(id);
        Actor actor = actorService.getActorByName(name);
        film.AddActor(actor);
        filmService.saveFilm(film.getId(), film);
        return "views/allFilms";
    }

    @GetMapping("/add/category")
    public String addCategoryPage() {
        return "views/newCategory";
    }

    @PostMapping("/add/category")
    public String addCategory(@RequestParam(name = "filmname") String filmname, @RequestParam(name = "name") String name, Model model) {
        Film film = filmService.getFilmByName(filmname);
        Category category = categoryService.getCategory(name);
        film.AddCategory(category);
        filmService.saveFilm(film.getId(), film);
        return "views/allFilms";
    }
}
