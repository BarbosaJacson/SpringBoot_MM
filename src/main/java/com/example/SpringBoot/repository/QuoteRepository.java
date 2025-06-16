package com.example.SpringBoot.repository; // Pacote onde esta interface está

import com.example.SpringBoot.model.Quote; // Importa a classe de entidade Quote
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List; // Adicionado para buscar lista de quotes
import java.util.Optional;

@Repository // Indica que esta interface é um componente de acesso a dados gerenciado pelo Spring
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    // Spring Data JPA magic: você pode definir métodos aqui e o Spring cria a implementação!

    // Exemplo: Buscar uma cotação por símbolo e timestamp (útil para verificar duplicatas)
    Optional<Quote> findBySymbolAndTimestamp(String symbol, LocalDateTime timestamp);

    // Exemplo: Buscar todas as cotações de um símbolo específico
    List<Quote> findBySymbol(String symbol);

    // Exemplo: Buscar cotações dentro de um período para um símbolo
    List<Quote> findBySymbolAndTimestampBetween(String symbol, LocalDateTime start, LocalDateTime end);
}