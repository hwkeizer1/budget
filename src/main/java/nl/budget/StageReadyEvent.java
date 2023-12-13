package nl.budget;

import org.springframework.context.ApplicationEvent;

import javafx.stage.Stage;

public class StageReadyEvent extends ApplicationEvent {

	private static final long serialVersionUID = 7386206334073996041L;

	public final Stage stage;

	public StageReadyEvent(Stage stage) {

		super(stage);
		this.stage = stage;
	}

}
