package nl.budget.service;

import org.springframework.stereotype.Service;

import javafx.collections.FXCollections;
import nl.budget.model.Account;
import nl.budget.repository.AccountRepository;
import nl.garvelink.iban.IBAN;
import nl.garvelink.iban.IBANException;

@Service
public class AccountService extends ListService<Account> {

	public AccountService(AccountRepository accountRepository) {
		repository = accountRepository;
		observableList = FXCollections.observableList(repository.findAll());
		comparator = (m1, m2) -> m1.getIban().compareTo(m2.getIban());
	}
	
	public boolean isValidNewIban(String iban) {
		boolean isValid = true;
		isValid &= !((AccountRepository)repository).existsByIban(iban);
		try {
			IBAN.valueOf(iban);
		} catch(IBANException e) {
			isValid &= false;
		}
		return isValid;
	}
}
