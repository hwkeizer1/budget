package nl.budget.view.post;

import org.springframework.stereotype.Component;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import nl.budget.view.AbstractView;

@Component
public class PostView extends AbstractView {

	private HBox mainView;

	public PostView() {
		initComponents();
	}

	@Override
	public Pane getView() {
		return mainView;
	}

	@Override
	public void initComponents() {
		// TODO: replace dummy content
		mainView = new HBox();
		Label label = new Label("Posten overzicht");
		mainView.getChildren().add(label);

	}

}
