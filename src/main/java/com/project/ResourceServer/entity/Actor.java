package com.project.ResourceServer.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="actors")
public class Actor {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator = "actors_seq")
    @SequenceGenerator(name="actors_seq",
            sequenceName = "actors_actorid_seq", allocationSize = 1)
    @Column(name = "actorid")
    private Long id;
    @Column(name = "actorname")
    private String name;
    @Column(name = "description")
    private  String description;
    @Column(name = "photo")
    private String photo;
    @ManyToMany(mappedBy = "actors",cascade = CascadeType.ALL)
    public List<Film> films = new ArrayList<Film>();


    public void removeFilms()
    {
        films.forEach(film -> film.getActors().remove(this));
    }

    public Actor() {

    }

    public Actor(Long id, String name,String description, String photo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
    }

    public List<Film> getFilms()
    {
        return films;
    }

    public void setFilms(List<Film> films){
        this.films = films;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return id == actor.id &&
                Objects.equals(name, actor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
