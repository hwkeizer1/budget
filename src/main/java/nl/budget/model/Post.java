package nl.budget.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Access(AccessType.PROPERTY)
public class Post implements Externalizable {

	private final transient LongProperty id = new SimpleLongProperty();

	@NotBlank
	@Column(unique = true)
	private final transient StringProperty category = new SimpleStringProperty();
	
	private final transient ObjectProperty<BigDecimal> reserve = new SimpleObjectProperty<>();
	
	private final transient ObjectProperty<BigDecimal> budget = new SimpleObjectProperty<>();
	
	private final transient ObjectProperty<BigDecimal> balance = new SimpleObjectProperty<>();
	
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
	
	public String getCategory() {
		return category.get();
	}

	public void setCategory(String category) {
		this.category.set(category);
	}

	public StringProperty categoryProperty() {
		return category;
	}
	
	public BigDecimal getReserve() {
		return reserve.get();
	}
	
	public void setReserve(BigDecimal reserve) {
		this.reserve.set(reserve);
	}
	
	public ObjectProperty<BigDecimal> reserveProperty() {
		return reserve;
	}
	
	public BigDecimal getBudget() {
		return budget.get();
	}
	
	public void setBudget(BigDecimal budget) {
		this.budget.set(budget);
	}
	
	public ObjectProperty<BigDecimal> budgetProperty() {
		return budget;
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
		return "[" + getId() + ", " + getCategory() + ", " + getReserve() + ", " + getBudget() + ", " + getBalance() + "]";
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setCategory((String) in.readObject());
		setReserve((BigDecimal)in.readObject());
		setBudget((BigDecimal)in.readObject());
		setBalance((BigDecimal)in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeObject(getCategory());
		out.writeObject(getReserve());
		out.writeObject(getBudget());
		out.writeObject(getBalance());
	}
}
