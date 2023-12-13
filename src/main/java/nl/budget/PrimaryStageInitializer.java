package nl.budget;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.budget.view.RootView;
import nl.budget.view.ViewMessage;

@Component
public class PrimaryStageInitializer implements ApplicationListener<StageReadyEvent> {

  private final RootView rootView;

  public PrimaryStageInitializer(RootView rootView) {
    this.rootView = rootView;
  }

  @Override
  public void onApplicationEvent(StageReadyEvent event) {
    Stage stage = event.stage;
    Scene scene = new Scene(rootView.asParent());
    scene.getStylesheets().clear();
    scene.getStylesheets().add(
    		getClass().getResource("/css/styles.css").toExternalForm()
    		);
    
    stage.setScene(scene);
    stage.setTitle(ViewMessage.PROGRAM_TITLE);
    stage.show();
    stage.setMaximized(true);
  }
}
