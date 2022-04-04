package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.models.Category;
import br.com.levelup.aluratech.models.response.CategoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.active = true")
    List<Category> findActiveCategories();

    @Query("SELECT new br.com.levelup.aluratech.models.response.CategoryResponse(c.name, c.code, c.active) " +
            "FROM Category c ORDER BY c.order")
    List<CategoryResponse> findAllSorted();
}
