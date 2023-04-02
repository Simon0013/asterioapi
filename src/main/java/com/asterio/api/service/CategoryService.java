package com.asterio.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import com.asterio.api.model.Category;
import com.asterio.api.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> listAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		for (Category category: categoryRepository.findAll()) {
			if (!category.getDeleted())
				categories.add(category);
		}
		return categories;
	}
	
	public void saveCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public Category getCategory(Long id) {
		Category category = categoryRepository.findById(id).get();
		if (!category.getDeleted())
			return category;
		return null;
	}
}
