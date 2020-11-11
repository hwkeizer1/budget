package nl.budget.views;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import nl.budget.application.StageReadyEvent;
import nl.budget.controllers.RootController;

@Component
public class PrimaryStageInitializer implements ApplicationListener<StageReadyEvent> {

	private final FxWeaver fxWeaver;

	public PrimaryStageInitializer(FxWeaver fxWeaver) {
		this.fxWeaver = fxWeaver;
	}
	
	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		Stage stage = event.stage;
		Scene scene = new Scene(fxWeaver.loadView(RootController.class));
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
		}
	
}
