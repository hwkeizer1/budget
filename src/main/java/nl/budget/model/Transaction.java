package nl.budget.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"journalDate", "number"})})
@Access(AccessType.PROPERTY)
public class Transaction implements Externalizable {

	@Transient
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	
	private final LongProperty id = new SimpleLongProperty();
	// TODO: find alternative for NotBlank
//	@NotBlank
	private final ObjectProperty<LocalDate> journalDate = new SimpleObjectProperty<>();

	// TODO: find alternative for NotBlank
//	@NotBlank
	private final IntegerProperty number = new SimpleIntegerProperty();
	
	private ObjectProperty<Account> account = new SimpleObjectProperty<>();
	
	private final StringProperty contraAccount = new SimpleStringProperty();
	
	// TODO: find alternative for NotBlank
//	@NotBlank
	private final ObjectProperty<BigDecimal> balance = new SimpleObjectProperty<>();
	
	// TODO: find alternative for NotBlank
//	@NotBlank
	private final StringProperty currencyType =  new SimpleStringProperty();
	
	// TODO: find alternative for NotBlank
//	@NotBlank
	private final ObjectProperty<BigDecimal> amount = new SimpleObjectProperty<>();
	
	private final StringProperty description = new SimpleStringProperty();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id.get();
	}
	
	public void setId(Long id) {
		this.id.set(id);
	}
	
	public LongProperty idProperty() {
		return id;
	}
	
	public LocalDate getJournalDate() {
		return journalDate.get();
	}
	
	public void setJournalDate(LocalDate journalDate) {
		this.journalDate.set(journalDate);
	}
	
	public ObjectProperty<LocalDate> journalDateProperty() {
		return journalDate;
	}
	
	public Integer getNumber() {
		return number.get();
	}
	
	public void setNumber(Integer number) {
		this.number.set(number);
	}
	
	public IntegerProperty numberProperty() {
		return number;
	}
	
	@Transient
	public String getTransactionId() {
		return journalDate.get().format(formatter) + String.valueOf(number.get());
	}
	
	@ManyToOne
	@JoinColumn(name="account", nullable=false)
	public Account getAccount() {
		return account.get();
	}
	
	public void setAccount(Account account) {
		this.account.set(account);
	}
	
	public ObjectProperty<Account> accountProperty() {
		return account;
	}
	
	public String getContraAccount() {
		return contraAccount.get();
	}
	
	public void setContraAccount(String contraAccount) {
		this.contraAccount.set(contraAccount);
	}

	public StringProperty contraAccountProperty() {
		return contraAccount;
	}
	
	public BigDecimal getBalance() {
		return balance.get();
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance.set(balance);
	}
	
	public ObjectProperty<BigDecimal> balanceProperty() {
		return balance;
	}
	
	public String getCurrencyType() {
		return currencyType.get();
	}
	
	public void setCurrencyType(String currencyType) {
		this.currencyType.set(currencyType);
	}
	
	public StringProperty currencyTypeProperty() {
		return currencyType;
	}
	
	public BigDecimal getAmount() {
		return amount.get();
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount.set(amount);
	}
	
	public ObjectProperty<BigDecimal> amountProperty() {
		return amount;
	}
	
	public String getDescription() {
		return description.get();
	}
	
	public void setDescription(String description) {
		this.description.set(description);
	}
	
	public StringProperty descriptionProperty() {
		return description;
	}
	
	@Override
	public String toString() {
		return "Transaction [formatter=" + formatter + ", id=" + id + ", journalDate=" + journalDate + ", number="
				+ number + ", transactionId=" + getTransactionId() + ", contraAccount=" + contraAccount + ", balance="
				+ balance + ", currencyType=" + currencyType + ", amount=" + amount + ", description=" + description
				+ "]";
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setJournalDate((LocalDate)in.readObject());
		setAccount((Account)in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeObject(getJournalDate());
		out.writeObject(getAccount());
	}


}
