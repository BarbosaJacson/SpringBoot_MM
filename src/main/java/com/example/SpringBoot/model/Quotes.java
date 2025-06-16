package com.example.SpringBoot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table; // Importar Table
import java.time.LocalDateTime; // Usar LocalDateTime para incluir hora/minuto

@Entity
@Table(name = "quotes") // IMPORTANTE: O nome da tabela no banco de dados será "quotes" (plural)
public class Quote { // IMPORTANTE: O nome da classe é "Quote" (singular)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "quote_timestamp", nullable = false) // Nome da coluna e não nula
    private LocalDateTime timestamp; // Alterado para timestamp para flexibilidade de tempo

    @Column(name = "open_price")
    private Double open;

    @Column(name = "highest_price")
    private Double highest;

    @Column(name = "lowest_price")
    private Double lowest;

    @Column(name = "closing_price")
    private Double closing;

    @Column(name = "volume")
    private Long volume;

    // Construtor vazio (obrigatório para JPA)
    public Quote() {}

    // Construtor com parâmetros para facilitar a criação de objetos
    public Quote(String symbol, LocalDateTime timestamp, Double open, Double highest, Double lowest, Double closing, Long volume) {
        this.symbol = symbol;
        this.timestamp = timestamp;
        this.open = open;
        this.highest = highest;
        this.lowest = lowest;
        this.closing = closing;
        this.volume = volume;
    }

    // Getters e Setters (no IntelliJ, você pode usar Alt+Insert para gerá-los automaticamente)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public Double getOpen() { return open; }
    public void setOpen(Double open) { this.open = open; }
    public Double getHighest() { return highest; }
    public void setHighest(Double highest) { return highest; }
    public Double getLowest() { return lowest; }
    public void setLowest(Double lowest) { return lowest; }
    public Double getClosing() { return closing; }
    public void setClosing(Double closing) { this.closing = closing; }
    public Long getVolume() { return volume; }
    public void setVolume(Long volume) { this.volume = volume; }

    // Opcional: toString() para facilitar o debug (Alt+Insert)
    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", timestamp=" + timestamp +
                ", open=" + open +
                ", highest=" + highest +
                ", lowest=" + lowest +
                ", closing=" + closing +
                ", volume=" + volume +
                '}';
    }
}