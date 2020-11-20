package nl.budget.controllers;

import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Slf4j
@Controller
@FxmlView("root.fxml")
public class RootController {
	
	private final FxWeaver fxWeaver;
	
	@FXML
	private BorderPane rootWindow;
	
	public RootController(FxWeaver fxWeaver) {
		this.fxWeaver = fxWeaver;
	}

	@FXML
	public void showPreferencesDialog(ActionEvent actionEvent) {
		fxWeaver.loadController(PreferencesDialogController.class).show();
		
	}
	
	@FXML
	public void showCreateAdministrationDialog(ActionEvent actionEvent) {
		fxWeaver.loadController(CreateAdministrationDialogController.class).show();
	}
	
	@FXML
	public void loadAdministration(ActionEvent actionEvent) {
		
	}
	
	@FXML
	public void closeAdministration(ActionEvent actionEvent) {
		
	}
}
