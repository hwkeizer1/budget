package nl.budget.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BankAccountTest {

	private static final String VALID_IBAN = "GB33BUKB20201555555555";
	private static final String VALID_IBAN_BANKCODE_UNKNOWN = "GB94BARC10201530093459";
	private static final String IBAN_INVALID_CHECKSUM = "GB2LABBY09012857201707";
	private static final String IBAN_INVALID_ACCOUNT_STRUCTURE = "GB12BARC20201530093A59";

	@Test
	void testInvalidBankAccounts() {
		BankAccount bankAccount = new BankAccount(IBAN_INVALID_CHECKSUM, BankAccountType.PAYMENTACCOUNT);
		assertFalse(bankAccount.isValid());
		assertNull(bankAccount.getIban());
		bankAccount = new BankAccount(IBAN_INVALID_ACCOUNT_STRUCTURE, BankAccountType.PAYMENTACCOUNT);
		assertFalse(bankAccount.isValid());
		assertNull(bankAccount.getIban());
	}
	
	@Test
	void testValidBankAccounts() {
		BankAccount bankAccount = new BankAccount(VALID_IBAN, BankAccountType.SAVINGACCOUNT);
		assertTrue(bankAccount.isValid());
		assertEquals(VALID_IBAN, bankAccount.getIban().toString());
		assertEquals(BankAccountType.SAVINGACCOUNT, bankAccount.getBankAccountType());
		bankAccount = new BankAccount(VALID_IBAN_BANKCODE_UNKNOWN, BankAccountType.PAYMENTACCOUNT);
		assertTrue(bankAccount.isValid());
		assertEquals(VALID_IBAN_BANKCODE_UNKNOWN, bankAccount.getIban().toString());
		assertEquals(BankAccountType.PAYMENTACCOUNT, bankAccount.getBankAccountType());
	}

}
