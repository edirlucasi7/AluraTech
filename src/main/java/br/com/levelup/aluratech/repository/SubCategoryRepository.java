package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    List<SubCategory> findAllByActiveTrueAndCategoryId(Long idCategory);
}
