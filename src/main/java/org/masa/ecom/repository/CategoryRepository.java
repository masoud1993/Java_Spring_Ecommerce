package org.masa.ecom.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.masa.ecom.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface    CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryName(@NotBlank @Size(min = 5, message = "Category name must contain at least 5 characters") String categoryName);
}
