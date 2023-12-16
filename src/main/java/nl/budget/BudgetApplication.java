package nl.budget;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import nl.budget.model.Account;
import nl.budget.model.Transaction;
import nl.budget.repository.AccountRepository;
import nl.budget.repository.TransactionRepository;
import nl.garvelink.iban.IBAN;

@Slf4j
@SpringBootApplication
public class BudgetApplication {

	public static void main(String[] args) {
		setDefaultSettings();
		Application.launch(BudgetApplicationFx.class, args);
	}

	private static void setDefaultSettings() {
		Locale nlLocal = new Locale.Builder().setLanguage("nl").setRegion("NL").build();
		Locale.setDefault(nlLocal);
	}

	/*
	 * Add some starting data for testing
	 */
	@Bean
	CommandLineRunner loadAccountData(AccountRepository accountRepository,
			TransactionRepository transactionRepository) {
		return args -> {
			IBAN iban = IBAN.valueOf("NL02ABNA0123456789");
			Account account = new Account();
			if (accountRepository.findByIban(iban.toString()).isEmpty()) {
				account.setIban(iban.toString());
				account.setAccountHolder("M.Pietersen");
				account.setDescription("betaalrekening");
				accountRepository.save(account);
			} else {
				account = accountRepository.findByIban(iban.toString()).get();
			}
			if (!transactionRepository.existsByJournalDateAndNumber(LocalDate.of(2023, 1, 19), 22469412)) {
				Transaction transaction = new Transaction();
				transaction.setAccount(account);
				transaction.setAmount(new BigDecimal("23.45"));
				transaction.setBalance(new BigDecimal("5000.56"));
				transaction.setContraAccount(iban.toString());
				transaction.setCurrencyType("EUR");
				transaction.setDescription("Test transaction");
				transaction.setJournalDate(LocalDate.of(2023, 1, 19));
				transaction.setNumber(22469412);
				transaction = transactionRepository.save(transaction);
				log.debug("UNIEKE TRANSACTIE DATUM: {}", transaction.getJournalDate().toString());
				log.debug("UNIEKE TRANSACTIE NUMMER: {}", transaction.getNumber());
				log.debug("UNIEKE TRANSACTIE ID: {}", transaction.getTransactionId());
			}
		};
	}
}
