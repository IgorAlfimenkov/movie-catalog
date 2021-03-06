package com.project.ResourceServer.service;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.project.ResourceServer.entity.Actor;
import com.project.ResourceServer.entity.Category;
import com.project.ResourceServer.entity.Film;
import com.project.ResourceServer.entity.User;
import com.project.ResourceServer.service.api.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.ResourceServer.repository.FilmRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {


    FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository)
    {
        this.filmRepository = filmRepository;
    }

    @Override
    public Film addFilm(Film film) {

        return filmRepository.save(film);
    }

    @Override
    public void deleteFilm(Long id) {

        filmRepository.deleteById(id);
    }

    @Override
    public Film saveFilm(Long id, Film film) {

       film.setId(id);
        return filmRepository.save(film);
    }

    @Override
    public Film getFilmById(Long id) {
        return filmRepository.findById(id).get();
    }


    @Override
    public Film getFilmByName(String filmName) {
        return filmRepository.getFilmByName(filmName);
    }

    @Override
    public void deleteActorsFromFilm(Film film) {

        List<Actor> actors = film.getActors();
        List<Actor> toRemove = new ArrayList<>();
        for (Actor actor: actors) {

             toRemove.add(actor);
        }
        film.getActors().removeAll(toRemove);
    }

    @Override
    public void deleteCategoriesFromFilm(Film film) {
        List<Category> categories = film.getCategories();
        List<Category> toRemove = new ArrayList<>();
        for (Category category : categories) {

            toRemove.add(category);
        }
        film.getCategories().removeAll(toRemove);
    }

    @Override
    public void deleteUsersFromFilm(Film film) {
        List<User> users = film.getUsers();
        List<User> toRemove = new ArrayList<User>();
        for(User user : users)
        {
            toRemove.add(user);
        }
        film.getUsers().removeAll(toRemove);
    }

    public List<Film> getFilmsByYear(int year) {

        return null;
    }

    @Override
    public List<Film> getFilmsByActor(Actor actor) {
        return null;
    }

    @Override
    public List<Film> getSortedByYearFilms() {
        return null;
    }

    @Override
    public List<Film> getSortedByRatingFilms() {
        return null;
    }

    @Override
    public List<Film> getAllFilms() {

        return filmRepository.findAll();
    }


}

