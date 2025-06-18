package com.example.SpringBoot.model; // Ajuste o pacote se necessário

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.time.LocalDate;

@Entity
public class AssetQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long symbol;

    @Column(name = "quote_date", unique = true)
    private LocalDate date;

    @Column(name = "open_price")
    private Double open;

    @Column(name = "highest_price")
    private Double highest;

    @Column(name = "lowest_price")
    private Double lowest;

    @Column(name = "closing_price")
    private Double closing;

       public AssetQuote() {}

    public AssetQuote(Long symbol, LocalDate date, Double open, Double highest, Double lowest, Double closing) {
        this.symbol = symbol;
        this.date = date;
        this.open = open;
        this.highest = highest;
        this.lowest = lowest;
        this.closing = closing;
    }

    public Long getSymbol() {
        return symbol;
    }

    public void setSymbol(Long symbol) {
        this.symbol = symbol;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHighest() {
        return highest;
    }

    public void setHighest(Double highest) {
        this.highest = highest;
    }

    public Double getLowest() {
        return lowest;
    }

    public void setLowest(Double lowest) {
        this.lowest = lowest;
    }

    public Double getClosing() {
        return closing;
    }

    public void setClosing(Double closing) {
        this.closing = closing;
    }
}
