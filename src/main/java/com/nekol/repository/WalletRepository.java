package com.nekol.repository;

import com.nekol.domain.dto.WalletDTO;
import com.nekol.domain.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query("SELECT new com.nekol.domain.dto.WalletDTO(name, icon, amount, color) FROM Wallet WHERE user.id = :userID")
    List<WalletDTO> retrieveAllByUser(@Param("userID") Long userId);


}