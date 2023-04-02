package com.asterio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.asterio.api.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
