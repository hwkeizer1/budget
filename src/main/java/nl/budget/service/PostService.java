package nl.budget.service;

import org.springframework.stereotype.Service;

import javafx.collections.FXCollections;
import nl.budget.model.Post;
import nl.budget.repository.PostRepository;

@Service
public class PostService  extends ListService<Post> {

	public PostService(PostRepository postRepository) {
		repository = postRepository;
		observableList = FXCollections.observableList(repository.findAll());
		comparator = (m1, m2) -> m1.getCategory().compareTo(m2.getCategory());	
	}
	
	public boolean postAlreadyExistsForThisPeriod(Post post) {
		return ((PostRepository)repository).existsByCategoryAndMonthYear(post.getCategory(), post.getMonthYear());
	}
}
