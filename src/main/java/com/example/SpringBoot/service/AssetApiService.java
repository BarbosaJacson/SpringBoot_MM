package com.example.SpringBoot.service;
import com.example.SpringBoot.model.AssetQuote;
import com.example.SpringBoot.repository.AssetQuoteRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Map;

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

            try {
                String apiUrl =
                        String.format("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=%s&outputsize=full&apikey=%s",
                        symbol, apiKey
                );

                String jsonData = restTemplate.getForObject(apiUrl, String.class);


                if (jsonData == null || jsonData.contains("Error Message") || jsonData.contains("Note")) {
                    System.err.println("Erro ou limite de API excedido ao buscar dados para " + symbol + ": " + jsonData);
                    return;
                }


                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(jsonData);


                JsonNode timeSeries = root.get("Time Series (Daily)");

                if (timeSeries == null) {
                    System.err.println("Não foi possível encontrar 'Time Series (Daily)' na resposta da API para " + symbol);
                    return;
                }


                Iterator<Map.Entry<String, JsonNode>> fields = timeSeries.fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> entry = fields.next();
                    String dateString = entry.getKey();
                    JsonNode values = entry.getValue();

                    try {
                        LocalDate quoteDate = LocalDate.parse(dateString);


                        if (!quoteDate.isBefore(fromDate) && !quoteDate.isAfter(toDate)) {
                            AssetQuote assetQuote = new AssetQuote();
                            assetQuote.setSymbol(symbol);
                            assetQuote.setDate(quoteDate);

                            assetQuote.setOpen(values.get("1. open").asDouble());
                            assetQuote.setHighest(values.get("2. high").asDouble());
                            assetQuote.setLowest(values.get("3. low").asDouble());
                            assetQuote.setClosing(values.get("4. close").asDouble());



                            assetQuoteRepository.save(assetQuote);
                        }
                    } catch (DateTimeParseException e) {
                        System.err.println("Erro ao parsear data '" + dateString + "': " + e.getMessage());
                    }
                }
                System.out.println("Dados históricos de " + symbol + " para o período " + fromDate + " a " + toDate + " salvos com sucesso.");

            } catch (Exception e) {
                System.err.println("Ocorreu um erro inesperado ao buscar e salvar dados para " + symbol + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
}
