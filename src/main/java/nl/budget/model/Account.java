package nl.budget.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Access(AccessType.PROPERTY)
public class Account implements Externalizable {

	private transient SimpleLongProperty id = new SimpleLongProperty();

	@NotBlank
	@Column(unique = true)
	private final transient StringProperty iban = new SimpleStringProperty();

	private final transient StringProperty accountHolder = new SimpleStringProperty();
	
	private final transient StringProperty description = new SimpleStringProperty();
	
	@NotNull
	private final transient ObjectProperty<BigDecimal> balance = new SimpleObjectProperty<>();

	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions;

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

	public String getIban() {
		return iban.get();
	}

	public void setIban(String iban) {
		this.iban.set(iban);
	}

	public StringProperty ibanProperty() {
		return iban;
	}

	public String getAccountHolder() {
		return accountHolder.get();
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder.set(accountHolder);
	}

	public StringProperty accountHolderProperty() {
		return accountHolder;
	}

	public String getDescription() {
		return description.get();
	}

	public void setDescription(String description) {
		this.description.set(description);
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

	@Override
	public String toString() {
		return getIban() + " - " + getDescription();
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setIban((String) in.readObject());
		setAccountHolder((String) in.readObject());
		setDescription((String) in.readObject());
		setBalance((BigDecimal)in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeObject(getIban());
		out.writeObject(getAccountHolder());
		out.writeObject(getDescription());
		out.writeObject(getBalance());
	}
}
