package com.project.ResourceServer.repository;

import com.project.ResourceServer.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {


    Film getFilmByName(String name);
    List<Film> findFilmsByYear(int Year);
    //List<Film> findFilmsByActor(Actor actor);
    //List<Film> findByOrOrderByYear();
    //List<Film> findByOrOrderByRating();

}
