package com.project.ResourceServer.service.api;

import com.project.ResourceServer.entity.Actor;
import com.project.ResourceServer.entity.Film;

import java.util.List;

public interface FilmService {

    Film addFilm(Film film);
    void deleteFilm(Film film);
    Film saveFilm(Long id, Film film);
    Film getFilmById(Long id);
    Film getFilmByName(String filmName);
    void deleteActorsFromFilm(Film film);
    void deleteCategoriesFromFilm(Film film);
    void deleteUsersFromFilm(Film film);
    //List<Film> getFilmsByYear(int year);
    List<Film> getFilmsByActor(Actor actor);
    List<Film> getSortedByYearFilms();
    List<Film> getSortedByRatingFilms();
    List<Film> getAllFilms();
}
