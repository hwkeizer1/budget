package nl.budget.model;

import java.util.HashSet;
import java.util.Set;

public class Administration {

	private String locationFolder;
	private Set<BankAccount> bankAccountList = new HashSet<>();
	private Budget budget;
	
	public Administration(String locationFolder) {
		this.locationFolder = locationFolder;
	}
	
	public boolean addBankAccount(BankAccount bankAccount) {
		if (bankAccount.isValid()) {
			bankAccountList.add(bankAccount);
		}
		return true;
	}
	
	public boolean removeBankAccount() {
		return true;
	}
}
