package nl.budget.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.budget.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	public boolean existsByCategoryAndMonthYear(String category, LocalDate monthYear);
	
	public Optional<Post> findByCategoryAndMonthYear(String category, LocalDate monthYear);

}
