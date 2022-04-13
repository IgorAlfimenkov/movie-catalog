package com.project.ResourceServer.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name ="customers")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
    generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq",
    sequenceName = "users_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @ManyToMany
    @JoinTable(
            name = "customer_film",
            joinColumns = {@JoinColumn(name="customer_id")},
            inverseJoinColumns = {@JoinColumn(name="film_id")}
    )
    List<Film> films = new ArrayList<Film>();

    public User() {
    }

    public User(Long id, String nickname, String email, String name, String surname) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public List<Film> getFilms()
    {
        return this.films;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, email, name, surname);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
