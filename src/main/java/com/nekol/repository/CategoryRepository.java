package com.nekol.repository;

import com.nekol.domain.dto.CategoryDTO;
import com.nekol.domain.entity.Category;
import com.nekol.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Boolean existsByName(String name);

    @Query("select new com.nekol.domain.dto.CategoryDTO (c.id, c.name, c.icon, c.color) from Category c where c.categoryParent.id = :categoryParent and c.user.id = :userId")
    List<CategoryDTO> getCategory(@Param("categoryParent") Long categoryId, @Param("userId") Long userid);
    Category findByName(String name);

    @Query("select c.id from Category c where c.categoryParent.name = :type")
    List<Long> getCategoryByType(@Param("type") String type);

}
