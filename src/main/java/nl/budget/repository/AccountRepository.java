package nl.budget.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.budget.model.Account;
import nl.garvelink.iban.IBAN;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByIban(String iban);
}
