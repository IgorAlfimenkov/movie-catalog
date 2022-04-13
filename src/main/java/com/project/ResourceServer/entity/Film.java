package com.project.ResourceServer.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name="film")
public class Film {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
             generator = "film_id_seq")
    @SequenceGenerator(name="film_id_seq",
             sequenceName = "films_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "filmname")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "poster")
    private String poster;
    @Column(name = "trailer")
    private String trailer;
    @Column(name = "year")
    private int year;
    @Column(name = "rating")
    private float rating;
    @Column(name = "duration")
    private int duration;
    @Column(name="companyname")
    private String company;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "film_actor",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    public List<Actor> actors = new ArrayList<Actor>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void removeActors(Film film)
    {
        actors.stream().filter(actor -> actor.getFilms().remove(this));
    }
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name ="film_category",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name="category_id")}
    )
    public List<Category> categories = new ArrayList<Category>();
    @ManyToMany(mappedBy = "films", cascade = CascadeType.ALL)
        public List<User> users = new ArrayList<User>();

    public Film()
    {

    }


    public Film(Long id, String name, int year, String company, int duration, double rating, String description, String poster, String trailer) {


    }

    public Film(Long id, String name, String description, String poster, String trailer, int year, float rating, int duration,  String company) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.poster = poster;
        this.trailer = trailer;
        this.year = year;
        this.rating = rating;
        this.duration = duration;
        this.company = company;
    }

    public void AddActor(Actor actor)
    {
        this.actors.add(actor);
    }
    public void AddCategory(Category category){this.categories.add(category);}

    public List<Actor> getActors()
    {
        return this.actors;
    }

    public void setActors(List<Actor> actors)
    {

        this.actors = actors;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Category category)
    {
        this.categories.add(category);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilmName() {
        return name;
    }

    public void setFilmName(String filmName) {
        this.name = filmName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id &&
                year == film.year &&
                Float.compare(film.rating, rating) == 0 &&
                duration == film.duration &&
                Objects.equals(name, film.name) &&
                Objects.equals(description, film.description) &&
                Objects.equals(poster, film.poster) &&
                Objects.equals(trailer, film.trailer);
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", filmName='" + name + '\'' +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                ", trailer='" + trailer + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", duration=" + duration +
                '}';
    }

    public String showActors()
    {
        String res ="Actors:";
        for (Actor actor:this.actors) {
            res += actor.getName() + ",";
        }
        return res;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, poster, trailer, year, rating, duration);
    }
}
