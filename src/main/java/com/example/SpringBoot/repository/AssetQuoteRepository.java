package com.example.SpringBoot.repository;

import com.example.SpringBoot.model.AssetQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AssetQuoteRepository extends JpaRepository<AssetQuote, Long> {
    Optional<AssetQuote> findByDate(LocalDate date);
}