package com.nekol.repository;

import com.nekol.domain.dto.TransactionDTO;
import com.nekol.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

        @Query("select new com.nekol.domain.dto.TransactionDTO(t.id, c.id,c.name, c.icon, c.color,  t.note,  t.price, t.lastModifiedDate, u.username) from Category c join Transaction t on t.category.id = c.id " +
                "join User u on u.id = c.user.id " +
                "where u.id = :userId")
    List<TransactionDTO> getTransactionByMonth(@Param("userId") Long userId);

}
