package com.project.ResourceServer.service.api;

import com.project.ResourceServer.entity.Category;
import com.project.ResourceServer.entity.Film;

import java.util.List;

public interface CategoryService {

    Category addCategory(Category category);
    void deleteCategory(Category category);
    void deleteFilmsFromCategory(Category category);
    Category saveCategory(Long id, Category category);
    Category getCategoryById(Long id);
    Category getCategory(String name);
    List<Category> getCategories();
}
