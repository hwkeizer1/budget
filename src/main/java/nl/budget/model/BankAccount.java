package nl.budget.model;

import java.math.BigDecimal;

import org.iban4j.Iban;
import org.iban4j.IbanFormatException;
import org.iban4j.IbanUtil;
import org.iban4j.InvalidCheckDigitException;
import org.iban4j.UnsupportedCountryException;

public class BankAccount {

	private Iban iban;
	private BigDecimal balance;
	private BankAccountType bankAccountType;
	private boolean valid = false;

	public BankAccount(String iban, BankAccountType bankAccountType) {
		try {
			IbanUtil.validate(iban);
			this.iban = Iban.valueOf(iban);
			this.bankAccountType = bankAccountType;
		} catch (IbanFormatException | InvalidCheckDigitException | UnsupportedCountryException e) {
			this.iban = null;
			setValid(false);
		}
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Iban getIban() {
		return iban;
	}
	
	public BankAccountType getBankAccountType() {
		return bankAccountType;
	}

	public boolean isValid() {
		return valid;
	}

	private void setValid(boolean valid) {
		this.valid = valid;
	}

}
