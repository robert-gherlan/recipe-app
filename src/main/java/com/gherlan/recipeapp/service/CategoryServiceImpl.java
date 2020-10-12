package com.gherlan.recipeapp.service;

import com.gherlan.recipeapp.model.Category;
import com.gherlan.recipeapp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl extends AbstractServiceImpl<Category, Long> implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> findByDescription(String description) {
        return categoryRepository.findByDescription(description);
    }
}
