package com.project.ResourceServer.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)/*,
    generator = "category_seq")
    @SequenceGenerator(name="category_seq",
    sequenceName = "category_id_seq",allocationSize = 1)*/
    @Column(name = "id")
    private Long id;
    @Column(name = "categoryname")
    private String name;

    @ManyToMany(mappedBy = "categories", cascade= CascadeType.DETACH)
    List<Film> films = new ArrayList<Film>();

    public Category() {
    }

    public Category(Long id, String categoryName) {
        this.id = id;
        this.name = categoryName;
    }

    public List<Film> getFilms()
    {
        return this.films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long categoryId) {
        this.id = id;
    }

    public String getCategoryName() {
        return name;
    }

    public void setCategoryName(String categoryName) {
        this.name = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + name + '\'' +
                '}';
    }
}
