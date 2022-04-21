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
        model.addAttribute("actors", actorService.getAllActors());
        return "views/allActors";
    }

    @PostMapping("/add")
    public String add( @ModelAttribute Actor actor, Model model) {

        model.addAttribute("actor", actorService.addActor(actor));
        return "views/getActor";
    }

    @PostMapping("/get/actor")
    public Actor save(@RequestParam(name = "name") String name,
                      @PathVariable Long id,
                      @RequestParam String photo,
                      @RequestParam String description) {
        Actor actor = new Actor(id, name, description, photo);
        return actorService.saveActor(actor.getId(), actor);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteActor(@PathVariable Long id, Model model) {
        actorService.deleteActor(id);
        return all(model);
    }

    @GetMapping("/get")
    public String getActorByName(@RequestParam(name = "name") String name, Model model) {
        Actor actor = actorService.getActorByName(name);
        model.addAttribute("actor", actor);
        return "views/getActor";
    }

    @GetMapping("/add")
    public String getAddActorForm(Model model) {

        model.addAttribute("actor", new Actor());
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

    @GetMapping("/edit/{id}")
    public String getUpdateActorForm(@PathVariable Long id,Model model){
        model.addAttribute("actor", actorService.getActor(id));
        return "views/updateActor";
    }

    @PostMapping("/edit/{id}")
    public String updateActor(@PathVariable Long id,Actor actor, Model model) {

        model.addAttribute("actor", actorService.saveActor(id, actor));
        return "views/getActor";
    }

}
