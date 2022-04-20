package com.project.ResourceServer.service;

import com.project.ResourceServer.entity.Actor;
import com.project.ResourceServer.entity.Film;
import com.project.ResourceServer.service.api.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.ResourceServer.repository.ActorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {


    private final ActorRepository actorRepository;
    private List<Actor> actors;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public Actor saveActor(Long id, Actor actor) {

        actor.setId(id);
        return actorRepository.save(actor);
    }

    @Override
    public Actor addActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public List<Film> deleteFilmsFromActor(Actor actor) {
        List<Film> films = actor.getFilms();
        List<Film> toRemove = new ArrayList<>();
        for (Film film : films) {
            toRemove.add(film);
        }
        films.removeAll(toRemove);
        return films;
    }

    @Override
    public void deleteActorFromFilm(Actor actor, List<Film> films) {

        for (Film film : films) {
            film.actors.remove(actor);
        }

    }

    @Override
    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }

    @Override
    public Actor getActor(Long id) {
        return actorRepository.findById(id).get();
    }

    @Override
    public Actor getActorByName(String name) {
        return actorRepository.findActorByName(name);
    }

    @Override
    public List<Actor> getActorsByFilm(Film film) {
        return null;
    }

    @Override
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }
}
