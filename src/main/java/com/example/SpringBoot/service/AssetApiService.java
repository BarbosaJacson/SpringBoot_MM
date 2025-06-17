package com.example.SpringBoot.service;
import com.example.SpringBoot.repository.AssetQuoteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class AssetApiService {

    private final AssetQuoteRepository assetQuoteRepository;
    private final RestTemplate restTemplate;

    @Value("${alphavantage.api.key}")
    private String apiKey;

    public AssetApiService(AssetQuoteRepository assetQuoteRepository, RestTemplate restTemplate) {
        this.assetQuoteRepository = assetQuoteRepository;
        this.restTemplate = restTemplate;
    }

        public void fetchAndSaveHistoricalAssetQuotes(String symbol, LocalDate fromDate, LocalDate toDate) {

        System.out.println("Método fetchAndSaveHistoricalAssetQuotes chamado para: " + symbol);
        System.out.println("Chave de API disponível (parcialmente oculta): " + apiKey.substring(0, 5) + "...");
    }


}