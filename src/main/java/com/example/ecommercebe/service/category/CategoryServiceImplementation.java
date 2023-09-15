package com.example.ecommercebe.service.category;

import com.example.ecommercebe.entity.Category;
import com.example.ecommercebe.exception.CategoryException;
import com.example.ecommercebe.repository.CategoryRepository;
import com.example.ecommercebe.request.CreateCategoryRequest;
import com.example.ecommercebe.request.UpdateCategoryRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImplementation implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CreateCategoryRequest request) {
        return null;
    }

    @Override
    public Category updateCategory(Long categoryId, UpdateCategoryRequest request) {
        return null;
    }

    @Override
    public void deleteCategory(Long categoryId) throws CategoryException {

    }

    @Override
    public Category getCategoryById(Long categoryId) throws CategoryException {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }
}
