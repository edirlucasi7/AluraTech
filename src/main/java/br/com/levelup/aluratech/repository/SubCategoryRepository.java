package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.controller.response.subcategory.SubCategoryResponse;
import br.com.levelup.aluratech.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    List<SubCategory> findAllByActiveTrueAndCategoryId(Long idCategory);

    @Query("SELECT new br.com.levelup.aluratech.controller.response.subcategory.SubCategoryResponse(s.name, s.code, s.active, s.category.code)" +
            " FROM SubCategory s WHERE s.category.code =:categoryCode ORDER BY s.order")
    List<SubCategoryResponse> findAllSorted(String categoryCode);

    Optional<SubCategory> findByCode(String subCategoryCode);
}
