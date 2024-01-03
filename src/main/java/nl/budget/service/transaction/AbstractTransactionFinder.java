package nl.budget.service.transaction;

import java.util.List;

import nl.budget.model.Account;
import nl.budget.model.Transaction;

public abstract class AbstractTransactionFinder {

	public abstract List<Transaction> searchNewTransactionsForAccount(Account account);

}
