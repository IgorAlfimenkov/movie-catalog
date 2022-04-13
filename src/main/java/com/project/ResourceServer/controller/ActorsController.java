package com.project.ResourceServer.controller;

import com.project.ResourceServer.entity.Actor;
import com.project.ResourceServer.entity.Film;
import com.project.ResourceServer.service.api.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.ResourceServer.service.api.ActorService;

import java.util.List;

@Controller
@RequestMapping("/actors")
public class ActorsController {


    private final ActorService actorService;
    private final FilmService filmService;

    @Autowired
    public ActorsController(ActorService actorService, FilmService filmService) {
        this.actorService = actorService;
        this.filmService = filmService;
    }


    @GetMapping("")
    public String all(Model model) {
        List<Actor> actors = actorService.getAllActors();
        model.addAttribute("actors", actors);
        return "views/allActors";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name,
                      @RequestParam String photo,
                      @RequestParam String description,
                      Model model) {


        Actor actor = new Actor(name, description, photo);
        actorService.addActor(actor);
        List<Actor> actors = actorService.getAllActors();
        model.addAttribute("actors", actors);
        return "views/allActors";
    }

    @PostMapping("/get/actor")
    public Actor save(@RequestParam(name = "name") String name,
                      @PathVariable Long id,
                      @RequestParam String photo,
                      @RequestParam String description) {
        Actor actor = new Actor(id, name, description, photo);
        return actorService.saveActor(actor.getId(), actor);
    }


    @GetMapping("/delete/{id}")
    public String deleteActor(@PathVariable Long id, Model model) {
        Actor actor = actorService.getActor(id);
        actor.setFilms(actorService.deleteFilmsFromActor(actor));
        actorService.saveActor(actor.getId(), actor);
        List<Film> films = filmService.getAllFilms();
        for (Film film : films) {
            if (film.getActors().contains(actor)) {

                film.getActors().remove(actor);
                filmService.saveFilm(film.getId(), film);
            }
        }
        actorService.deleteActor(actor);
        return all(model);
    }

    @GetMapping("/get")
    public String getActorByName(@RequestParam(name = "name") String name, Model model) {
        Actor actor = actorService.getActorByName(name);
        model.addAttribute("actor", actor);
        return "views/getActor";
    }

    @GetMapping("/add")
    public String getAddActorForm() {
        return "views/addActor";
    }

    @GetMapping("/get/{id}")
    public String getActor(@PathVariable Long id, Model model) {
        Actor actor = actorService.getActor(id);
        model.addAttribute("actor", actor);
        return "views/getActor";
    }

    @GetMapping("/get/film")
    public List<Actor> getActorsByFilm(@RequestBody Film film) {
        return actorService.getActorsByFilm(film);
    }
}
