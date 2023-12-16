package nl.budget.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import nl.garvelink.iban.IBAN;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"journalDate", "number"})})
public class Transaction {

	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	@Transient
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NotBlank
	LocalDate journalDate;

//	@NotBlank
	int number;
	
	@Setter(AccessLevel.NONE)
	@Transient
	String transactionId;
	
	@ManyToOne
	@JoinColumn(name="account", nullable=false)
	Account account;
	
	String contraAccount;
	
//	@NotBlank
	BigDecimal balance;
	
	@NotBlank
	String currencyType;
	
//	@NotBlank
	BigDecimal amount;
	
	String description;
	
	public String getTransactionId() {
		return journalDate.format(formatter) + String.valueOf(number);
	}
}
