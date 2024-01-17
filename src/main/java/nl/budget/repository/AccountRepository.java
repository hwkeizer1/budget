package nl.budget.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.budget.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	public Optional<Account> findByIban(String iban);
	
	public boolean existsByIban(String iban);
}
