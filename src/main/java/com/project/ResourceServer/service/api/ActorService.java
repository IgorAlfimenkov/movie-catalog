package com.project.ResourceServer.service.api;

import com.project.ResourceServer.entity.Actor;
import com.project.ResourceServer.entity.Film;

import java.util.List;

public interface ActorService {


    Actor saveActor(Long id,Actor actor);
    Actor addActor(Actor actor);
    List<Film> deleteFilmsFromActor(Actor actor);
    void deleteActorFromFilm(Actor actor,List<Film> films);
    void deleteActor(Long id);
    Actor getActor(Long id);
    Actor getActorByName(String name);
    List <Actor> getActorsByFilm(Film f);
    List<Actor> getAllActors();

}
