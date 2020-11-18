package nl.budget.controllers;

import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("root.fxml")
public class RootController {
	
	private final FxWeaver fxWeaver;
	
	@FXML
	private BorderPane rootWindow;
	
	@FXML
	private Button loadAllAvailableTransactions;
	
	@FXML 
	private Button setPreferencesDialogButton;
	
	public RootController(FxWeaver fxWeaver) {
		this.fxWeaver = fxWeaver;
	}

	@FXML
	public void handleSetPreferencesDialog(ActionEvent actionEvent) {
		fxWeaver.loadController(PreferencesDialogController.class).show();
		
	}
}
