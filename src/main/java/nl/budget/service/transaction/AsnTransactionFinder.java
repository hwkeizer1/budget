package nl.budget.service.transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;
import nl.budget.model.Account;
import nl.budget.model.Transaction;
import nl.budget.service.ConfigService;
import nl.budget.view.ViewConstant;
import nl.budget.view.ViewMessage;

@Slf4j
public class AsnTransactionFinder extends AbstractTransactionFinder {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	@Override
	public List<Transaction> searchNewTransactionsForAccount(Account account) {
		List<Transaction> transactions = new ArrayList<>();
		for (Path file : findTransactionFiles(account)) {
			transactions.addAll(findTransactions(account, file));
		}
		return transactions;
	}
	
	protected List<Path> findTransactionFiles(Account account) {
		Path downloadPath = Paths.get(ConfigService.getConfigProperty(ViewConstant.DOWNLOAD_FOLDER_PROP));
		List<Path> files = new ArrayList<>();
		try (Stream<Path> stream = Files.list(downloadPath)) {
			return stream
					.filter(file -> !Files.isDirectory(file))
					.filter(file -> file.getFileName().toString().contains(account.getIban()))
					.toList();
		} catch (IOException e) {
			log.error(ViewMessage.ERROR_FINDING_FILES_IN_DOWNLOAD_FOLDER, e.getMessage());
		}
		return files;
	}

	protected List<Transaction> findTransactions(Account account, Path transactionFile) {
		List<String> data = new ArrayList<>();
		try {
			Stream<String> lines = Files.lines(transactionFile);
			data = lines.toList();
			lines.close();
		} catch (IOException e) {
			log.error(ViewMessage.ERROR_OPENING_FILES_IN_DOWNLOAD_FOLDER, e.getMessage());
		}
		
		List<Transaction> transactions = new ArrayList<>();
		for (String line : data) {
			transactions.add(parseStringToTransaction(account, line));
		}
		return transactions;
	}
	
	protected Transaction parseStringToTransaction(Account account, String line) {
		String[] field = line.split(",", 0);
		Transaction transaction = new Transaction();
		if (field[1].equals(account.getIban())) {
			transaction.setAccount(account);
		} else {
			// TODO: Implement correct error handling
			log.error("Onverwachte fout bij het inladen van transactie!!!");
		}
		transaction.setJournalDate(LocalDate.parse(field[0], formatter));
		transaction.setCurrencyType(field[7]);
		transaction.setBalance(new BigDecimal(field[8]));
		transaction.setAmount(new BigDecimal(field[10]));
		transaction.setNumber(Integer.valueOf(field[15]));
		transaction.setDescription(field[17].replaceAll("^\'|\'$", ""));
		
		return transaction;
	}

}
