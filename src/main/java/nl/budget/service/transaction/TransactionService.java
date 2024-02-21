package nl.budget.service.transaction;

import java.util.ArrayList;
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
		comparator = (m1, m2) -> m2.getTransactionId().compareTo(m1.getTransactionId());
	}

	public List<Transaction> searchNewTransactions(List<Account> accounts) {
		AbstractTransactionFinder finder = new AsnTransactionFinder(); // default for now....
		List<Transaction> newTransactions = new ArrayList<>();
		List<Transaction> foundTransactions = new ArrayList<>();
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
			foundTransactions.addAll(finder.searchNewTransactionsForAccount(account));
			
			// TODO: Transactions should be validated on missing transaction (correct order is essential!!!) as well
			for (Transaction transaction : foundTransactions) {
				// TODO: Investigate if usage of a generic repository in ListService makes sense because we need a cast anyway...
				if (!((TransactionRepository) repository).existsByJournalDateAndNumber(transaction.getJournalDate(), transaction.getNumber())) {
					newTransactions.add(transaction);
				}
			}
		}
		return saveAll(newTransactions);
	}
	
	public List<Transaction> findTransactionsWithoutPost() {
		// TODO: Investigate if usage of a generic repository in ListService makes sense because we need a cast anyway...
		List<Transaction> transactions = ((TransactionRepository)repository).findByPost(null);
		transactions.sort(comparator);
		return transactions;
	}

}
