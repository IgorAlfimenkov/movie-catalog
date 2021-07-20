package com.project.ResourceServer.controller;

import com.project.ResourceServer.entity.Actor;
import com.project.ResourceServer.entity.Film;
import com.project.ResourceServer.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.ResourceServer.service.ActorService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/actors")
public class ActorsController {



    ActorService actorService;
    FilmService filmService;

    @Autowired
    public ActorsController(ActorService actorService, FilmService filmService) {
        this.actorService = actorService;
        this.filmService = filmService;
    }


    @GetMapping("/all")
    public String all(Model model)
    {
        List<Actor> actors = actorService.getAllActors();
        model.addAttribute("actors",actors);
        return "views/allActors";
    }

    @PostMapping("/all")
    public String add(@RequestParam String name,
                      @RequestParam Long id,
                      @RequestParam String photo,
                      @RequestParam String description,
                      Model model)
    {


        Actor actor = new Actor(id,name, description,photo);
        actorService.addActor(actor);
        List<Actor> actors = actorService.getAllActors();
        model.addAttribute("actors",actors);
        return "views/allActors";
    }

    @PostMapping("/get/actor")
    public Actor save(@RequestParam(name="name") String name,
                      @PathVariable Long id,
                      @RequestParam String photo,
                      @RequestParam String description)
    {
        Actor actor = new Actor(id,name, description,photo);
        return actorService.saveActor(actor.getId(),actor);
    }



    @DeleteMapping("/delete/{id}")
    public String deleteActor(@PathVariable Long id)
    {
        Actor actor = actorService.getActor(id);
        actor.setFilms(actorService.deleteFilmsFromActor(actor));
        actorService.saveActor(actor.getId(),actor);
        List<Film> films = filmService.getAllFilms();
        for (Film film : films) {
            if(film.getActors().contains(actor)){

                film.getActors().remove(actor);
                filmService.saveFilm(film.getId(),film);
            }
        }
        actorService.deleteActor(actor);
        return "views/allActors";
    }

    @GetMapping("/get")
    public String getActorByName(@RequestParam(name = "name") String name,Model model)
    {
        Actor actor = actorService.getActorByName(name);
        model.addAttribute("actor", actor);
        return "views/getActor";
    }

    @GetMapping("/get/{id}")
    public String getActor(@PathVariable Long id, Model model)
    {
        Actor actor = actorService.getActor(id);
        model.addAttribute("actor", actor);
        return "views/getActor";
    }

    @GetMapping("/get/film")
    public List<Actor> getActorsByFilm(@RequestBody Film film)
    {
        return  actorService.getActorsByFilm(film);
    }
}
