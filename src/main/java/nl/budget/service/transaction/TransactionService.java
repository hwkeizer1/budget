package nl.budget.service.transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import javafx.collections.FXCollections;
import nl.budget.model.Account;
import nl.budget.model.Transaction;
import nl.budget.repository.TransactionRepository;
import nl.budget.service.ListService;
import nl.budget.view.ViewConstant;
import nl.garvelink.iban.IBAN;
import nl.garvelink.iban.IBANFields;

@Service
public class TransactionService extends ListService<Transaction> {

	public TransactionService(TransactionRepository transactionRepository) {
		repository = transactionRepository;
		observableList = FXCollections.observableList(repository.findAll());
		comparator = (m1, m2) -> m1.getTransactionId().compareTo(m2.getTransactionId());
	}

	public void searchNewTransactions(List<Account> accounts) {
		AbstractTransactionFinder finder = new AsnTransactionFinder(); // default for now....

		for (Account account : accounts) {
			Optional<String> bankCode = IBANFields.getBankIdentifier(IBAN.valueOf(account.getIban()));
			if (bankCode.isPresent()) {
				switch (bankCode.get()) {
				case ViewConstant.ASN:
					finder = new AsnTransactionFinder();
					break;
				case ViewConstant.ABNAMRO:
					// not implemented
					break;
				case ViewConstant.ING:
					// not implemented
					break;
				case ViewConstant.TRIODOS:
					// not implemented
					break;
				default:
					throw new IllegalStateException("Invalid bank code: " + bankCode.get());
				}
			}
			List<Transaction> newTransactions = finder.searchNewTransactionsForAccount(account);
			saveAll(newTransactions);
		}
	}

}
