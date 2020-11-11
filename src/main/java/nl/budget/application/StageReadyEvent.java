package nl.budget.application;

import org.springframework.context.ApplicationEvent;

import javafx.stage.Stage;

public class StageReadyEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1937024532729651688L;

	public final Stage stage;
	
	public StageReadyEvent(Stage stage) {
		super(stage);
		this.stage = stage;
	}
}
