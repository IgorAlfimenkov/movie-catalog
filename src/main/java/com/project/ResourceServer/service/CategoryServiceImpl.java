package com.project.ResourceServer.service;

import com.project.ResourceServer.entity.Category;
import com.project.ResourceServer.entity.Film;
import com.project.ResourceServer.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.ResourceServer.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRep)
    {
        this.categoryRepository = categoryRep;
    }

    @Override
    public Category addCategory(Category category) {

        category.setId(null);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public void deleteFilmsFromCategory(Category category) {

        List<Film> films = category.getFilms();
        List<Film> toRemove = new ArrayList<Film>();
        for (Film film:films) {
            toRemove.add(film);
        }
        films.removeAll(toRemove);
    }

    @Override
    public Category saveCategory(Long id,Category category) {

        Category c = categoryRepository.findById(id).get();
        c.setCategoryName(category.getCategoryName());
        return categoryRepository.save(c);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category getCategory(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
