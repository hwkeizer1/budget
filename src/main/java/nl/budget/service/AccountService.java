package nl.budget.service;

import org.springframework.stereotype.Service;

import javafx.collections.FXCollections;
import nl.budget.model.Account;
import nl.budget.repository.AccountRepository;

@Service
public class AccountService extends ListService<Account> {

	public AccountService(AccountRepository accountRepository) {
		repository = accountRepository;
		observableList = FXCollections.observableList(repository.findAll());
		comparator = (m1, m2) -> m1.getIban().compareTo(m2.getIban());
	}
	
	public boolean isValidNewAccount(Account account) {
		boolean isValid = true;
		// TODO: validate account
			// valid IBAN
			// Account does not exists
		return isValid;
	}
}
