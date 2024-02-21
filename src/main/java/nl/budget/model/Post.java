package nl.budget.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"category", "monthYear"})})
@Access(AccessType.PROPERTY)
public class Post implements Externalizable {

	private final transient LongProperty id = new SimpleLongProperty();

	@NotBlank
	private final transient StringProperty category = new SimpleStringProperty();

	private final transient ObjectProperty<BigDecimal> startBalance = new SimpleObjectProperty<>();

	private final transient ObjectProperty<BigDecimal> budget = new SimpleObjectProperty<>();

	private final transient ObjectProperty<BigDecimal> endBalance = new SimpleObjectProperty<>();

	private transient ObjectProperty<Account> account = new SimpleObjectProperty<>();

	private final transient ObjectProperty<LocalDate> monthYear = new SimpleObjectProperty<>();

	@OneToMany(mappedBy = "post")
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

	public String getCategory() {
		return category.get();
	}

	public void setCategory(String category) {
		this.category.set(category);
	}

	public StringProperty categoryProperty() {
		return category;
	}

	public BigDecimal getStartBalance() {
		if (startBalance.get() == null) {
			setStartBalance(new BigDecimal("0.00"));
		}
		return startBalance.get();
	}

	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance.set(startBalance);
	}

	public ObjectProperty<BigDecimal> startBalanceProperty() {
		return startBalance;
	}

	public BigDecimal getBudget() {
		if (budget.get() == null) {
			setBudget(new BigDecimal("0.00"));
		}
		return budget.get();
	}

	public void setBudget(BigDecimal budget) {
		this.budget.set(budget);
	}

	public ObjectProperty<BigDecimal> budgetProperty() {
		return budget;
	}

	public BigDecimal getEndBalance() {
		if (endBalance.get() == null) {
			setEndBalance(new BigDecimal("0.00"));
		}
		return endBalance.get();
	}

	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance.set(endBalance);
	}

	public ObjectProperty<BigDecimal> endBalanceProperty() {
		return endBalance;
	}

	@ManyToOne
	@JoinColumn(name = "account", nullable = false)
	public Account getAccount() {
		return account.get();
	}

	public void setAccount(Account account) {
		this.account.set(account);
	}

	public ObjectProperty<Account> accountProperty() {
		return account;
	}

	public LocalDate getMonthYear() {
		if (monthYear.get() == null) {
			setMonthYear(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1));
		}
		return monthYear.get();
	}

	public void setMonthYear(LocalDate monthYear) {
		this.monthYear.set(LocalDate.of(monthYear.getYear(), monthYear.getMonth(), 1));
	}

	public ObjectProperty<LocalDate> monthYearProperty() {
		return monthYear;
	}

	public String toFullString() {
		return getId() + ", " + getCategory() + ", " + getStartBalance() + ", " + getBudget() + ", " + getEndBalance()
				+ ", " + getMonthYear();
	}

	@Override
	public String toString() {
		return getCategory();
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setCategory((String) in.readObject());
		setStartBalance((BigDecimal) in.readObject());
		setBudget((BigDecimal) in.readObject());
		setEndBalance((BigDecimal) in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeObject(getCategory());
		out.writeObject(getStartBalance());
		out.writeObject(getBudget());
		out.writeObject(getEndBalance());
	}
}
