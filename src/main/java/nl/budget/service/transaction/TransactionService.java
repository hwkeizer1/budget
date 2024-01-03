package nl.budget.service.transaction;


import java.util.List;

import org.springframework.stereotype.Service;

import javafx.collections.FXCollections;
import nl.budget.model.Account;
import nl.budget.model.Transaction;
import nl.budget.repository.AccountRepository;
import nl.budget.repository.TransactionRepository;
import nl.budget.service.ListService;


@Service
public class TransactionService extends ListService<Transaction> {
	
	// TODO: fix this dependency (see below)!!!!
	private final AccountRepository accountRepository;
	
	public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
		repository = transactionRepository;
		observableList = FXCollections.observableList(repository.findAll());
		comparator = (m1, m2) -> m1.getTransactionId().compareTo(m2.getTransactionId());
	}
	
	public void searchNewTransactions() {
		
		// TODO: fix this 'quick and dirty' solution
		// Accounts must be given by the user, can be more accounts, all should be checked...
		Account account = accountRepository.findByIban("NL02ABNA0123456789").get();
		AbstractTransactionFinder finder = new AsnTransactionFinder();
		List<Transaction> newTransactions = finder.searchNewTransactionsForAccount(account);
		// TODO: Validate the new transactions:
		//		- They should not already exist (unique transaction id)
		// 		- They should be in correct order (based on transaction id) without missing
		//		  transactions (balance should be correct after processing transaction)
		saveAll(newTransactions);
	}
	
}
