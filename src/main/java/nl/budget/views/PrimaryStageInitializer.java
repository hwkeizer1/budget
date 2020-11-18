package nl.budget.views;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxWeaver;
import nl.budget.controllers.RootController;

@Slf4j
@Component
public class PrimaryStageInitializer implements ApplicationListener<StageReadyEvent> {

	private final FxWeaver fxWeaver;
//	private final Administration administration;

	public PrimaryStageInitializer(FxWeaver fxWeaver) {
		this.fxWeaver = fxWeaver;
//		this.administration = administration;
	}

	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		log.debug("StageReadyEvent received");
		Stage stage = event.stage;
		Scene scene = new Scene(fxWeaver.loadView(RootController.class), 800, 600);
		stage.setScene(scene);
		stage.setTitle("Budget 1.0"); // TODO improve
		stage.show();
//		stage.setMaximized(true);
		}
	
}
