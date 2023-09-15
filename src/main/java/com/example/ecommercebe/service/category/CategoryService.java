package com.example.ecommercebe.service.category;

import com.example.ecommercebe.entity.Category;
import com.example.ecommercebe.exception.CategoryException;
import com.example.ecommercebe.request.CreateCategoryRequest;
import com.example.ecommercebe.request.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {

    Category createCategory(CreateCategoryRequest request);

    Category updateCategory(Long categoryId, UpdateCategoryRequest request);

    void deleteCategory(Long categoryId) throws CategoryException;

    Category getCategoryById(Long categoryId) throws CategoryException;

    List<Category> getAllCategories();
}
