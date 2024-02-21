package nl.budget;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javafx.application.Application;
import nl.budget.model.Account;
import nl.budget.model.Post;
import nl.budget.model.Transaction;
import nl.budget.repository.AccountRepository;
import nl.budget.repository.PostRepository;
import nl.budget.repository.TransactionRepository;
import nl.garvelink.iban.IBAN;

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
			TransactionRepository transactionRepository, PostRepository postRepository) {
		return args -> {
			IBAN iban = IBAN.valueOf("NL02ABNA0123456789");
			Account account = new Account();
			if (!accountRepository.existsByIban(iban.toPlainString())) {
				account.setIban(iban.toPlainString());
				account.setAccountHolder("M.Pietersen");
				account.setDescription("gezamenlijke betaalrekening");
				account.setBalance(new BigDecimal("322.05"));
				accountRepository.save(account);
			} else {
				account = accountRepository.findByIban(iban.toPlainString()).get();
			}
			
			Post post = new Post();
			if (!postRepository.existsByCategoryAndMonthYear("Voeding", LocalDate.of(2023, 10, 1))) {
				post.setCategory("Voeding");
				post.setStartBalance(new BigDecimal("200.00"));
				post.setBudget(new BigDecimal("400.00"));
				post.setEndBalance(new BigDecimal("123.89"));
				post.setAccount(account);
				post.setMonthYear(LocalDate.of(2023, 10, 1));
				postRepository.save(post);
			} else {
				post = postRepository.findByCategoryAndMonthYear("Voeding", LocalDate.of(2023, 10, 1)).get();
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
				transaction.setPost(post);
				transaction = transactionRepository.save(transaction);
			}
		};
	}
}
