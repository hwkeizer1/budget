package nl.budget.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.budget.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	public Optional<Post> findByCategory(String category);

	public boolean existsByCategory(String category);
}
