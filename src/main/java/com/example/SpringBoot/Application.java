package com.example.SpringBoot;
import com.example.SpringBoot.service.AssetApiService; // IMPORTAR AssetApiService
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner run(AssetApiService assetApiService) {
		return args -> {

			Scanner scanner = new Scanner(System.in);
			LocalDate startDate = null;
			LocalDate endDate = null;

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			System.out.println("--- Ferramenta de Cotações de Ativos ---");

			System.out.print("Enter with a symbol (ex: IBM, AAPL, PETR4.SA): ");
			String symbol = scanner.nextLine().trim().toUpperCase();

			while (startDate == null) {
				System.out.print("Start Date (dd/MM/yyyy): ");
				String dateStartString = scanner.nextLine();
				try {
					startDate = LocalDate.parse(dateStartString, formatter);
				} catch (DateTimeParseException e) {
					System.out.println("Invalid date. Please, use the format dd/MM/yyyy.");
				}
			}

			while (endDate == null) {
				System.out.print("End date (dd/MM/yyyy): ");
				String dateEndString = scanner.nextLine();
				try {
					endDate = LocalDate.parse(dateEndString, formatter);
				} catch (DateTimeParseException e) {
					System.out.println("Invalid date. Please, use the format dd/MM/yyyy.");
				}
			}

			if (startDate.isAfter(endDate)) {
				System.out.println("The Start Date cannot be later than the End Date. Closing.");
				scanner.close();
				return;
			}
			System.out.println("\n--- Searching data... ---");
			assetApiService.fetchAndSaveHistoricalAssetQuotes(symbol, startDate, endDate);
			System.out.println("--- Process Ready ---");

			scanner.close();
		};

	}


}
