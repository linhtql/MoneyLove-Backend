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

    @Query("select new com.nekol.domain.dto.CategoryDTO (c.id, c.name, c.icon, c.color) from Category c where c.categoryParent.id = :categoryParent and (c.user.id = :userIdAdmin or c.user.id = :userIdUser)")
    List<CategoryDTO> getCategory(@Param("categoryParent") Long categoryId, @Param("userIdAdmin") Long userIdAdmin, @Param("userIdUser") Long userIdUser);
    Category findByName(String name);

    @Query("select c.id from Category c where c.categoryParent.name = :type")
    List<Long> getCategoryByType(@Param("type") String type);

    @Query("select c.name, sum(t.price) as total from Transaction t " +
            "join Wallet w on t.wallet.id = w.id " +
            "join User u on u.id = w.user.id " +
            "join Category c on c.id = t.category.id " +
            "where u.id = :userId and c.categoryParent.id = :categoryParent " +
            "group by c.name")
    List<Object[]> statistic(@Param("userId") Long userId, @Param("categoryParent") Long categoryParent);

    @Query("select new com.nekol.domain.dto.CategoryDTO (c.id, c.name, c.icon, c.color) from Category c where c.categoryParent.id = :categoryParent and c.user.id = :userIdUser")
    List<CategoryDTO> getCategoryByUserAndType(@Param("categoryParent") Long categoryId,  @Param("userIdUser") Long userIdUser);

}
