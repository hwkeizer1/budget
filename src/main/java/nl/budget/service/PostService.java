package nl.budget.service;

import java.time.LocalDate;
import java.util.List;

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
	
	public List<Post> getPostsForMonthYear(LocalDate monthYear) {
		List<Post> posts = ((PostRepository)repository).findByMonthYear(monthYear);
		posts.sort(comparator);
		return posts;
	}
}
