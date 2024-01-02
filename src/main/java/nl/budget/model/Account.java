package nl.budget.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
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
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Access(AccessType.PROPERTY)
public class Account implements Externalizable {
	
	private SimpleLongProperty id = new SimpleLongProperty();
	
	@NotBlank
	@Column(unique=true)
	private final StringProperty iban = new SimpleStringProperty();
	
	private final StringProperty accountHolder = new SimpleStringProperty();
	private final StringProperty description = new SimpleStringProperty();
	
	@OneToMany(mappedBy="account")
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
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", iban=" + iban + ", accountHolder=" + accountHolder + ", description="
				+ description + "]";
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setIban((String)in.readObject());
		setAccountHolder((String)in.readObject());
		setDescription((String)in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeObject(getIban());
		out.writeObject(getAccountHolder());
		out.writeObject(getDescription());
	}
}
