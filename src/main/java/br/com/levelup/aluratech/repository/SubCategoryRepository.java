package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    @Query("SELECT DISTINCT s FROM SubCategory s WHERE s.active = true AND s.category.id =:idSubCategory")
    List<SubCategory> findActiveSubCategoriesByCategoryId(Long idSubCategory);
}
