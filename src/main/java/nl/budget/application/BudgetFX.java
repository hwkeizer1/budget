package nl.budget.application;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import nl.budget.views.StageReadyEvent;

public class BudgetFX extends Application {

	private ConfigurableApplicationContext applicationContext;
	
	@Override
	public void init() {
		String[] args = getParameters().getRaw().toArray(new String[0]);
		this.applicationContext = new SpringApplicationBuilder()
				.sources(Budget.class)
				.run(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		applicationContext.publishEvent(new StageReadyEvent(primaryStage));
		
	}
	
	@Override 
	public void stop() {
		this.applicationContext.close();
		Platform.exit();
	}
}
