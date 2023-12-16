package nl.budget.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.budget.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	public boolean existsByJournalDateAndNumber(LocalDate journalDate, int number);
}
