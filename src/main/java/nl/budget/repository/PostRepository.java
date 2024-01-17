package nl.budget.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.budget.model.Account;
import nl.budget.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	public Optional<Account> findByCategory(String iban);

}
