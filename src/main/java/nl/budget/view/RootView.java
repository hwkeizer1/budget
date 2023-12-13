package nl.budget.view;

import org.springframework.stereotype.Component;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

@Component
public class RootView {

	private BorderPane rootWindow;

	public RootView() {
		rootWindow = new BorderPane();
	}

	public Parent asParent() {
		return rootWindow;
	}
}
