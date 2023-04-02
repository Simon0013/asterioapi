package com.asterio.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.asterio.api.model.*;
import com.asterio.api.service.*;

@RestController
@RequestMapping("/cat")
public class CategoryController {
	@Autowired
	CategoryService service;
	
	@Autowired
	BannerInCategoryService bicService;
	
	@GetMapping
	public List<Category> list() {
		return service.listAllCategories();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable Long id) {
		try {
            Category category = service.getCategory(id);
            return new ResponseEntity<Category>(category, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping
	public ResponseEntity<List<Category>> getByBanners(@RequestParam List<Long> bid) {
		List<Category> categories = new ArrayList<Category>();
		List<Long> categoryIds = new ArrayList<Long>();
		try {
			for (long banner: bid) {
				List<BannerInCategory> bics = bicService.listAllByBanner(banner);
				for (BannerInCategory bic: bics) {
					if (!categoryIds.contains(bic.getBanner())) {
						categories.add(service.getCategory(bic.getCategory()));
						categoryIds.add(bic.getCategory());
					}
				}
			}
			return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<Category>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/")
	public void add(@RequestBody Category category) {
		service.saveCategory(category);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Category category, @PathVariable Long id) {
		try {
			Category existCategory = service.getCategory(id);
			category.setId(id);
			service.saveCategory(category);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			Category existCategory = service.getCategory(id);
			existCategory.setDeleted(true);
			service.saveCategory(existCategory);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
