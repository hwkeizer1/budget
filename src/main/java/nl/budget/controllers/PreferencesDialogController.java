package nl.budget.controllers;

import java.io.File;

import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import nl.budget.model.BudgetPreferences;

@Controller
@FxmlView("preferencesDialog.fxml")
public class PreferencesDialogController {
	
	private BudgetPreferences prefs;

	private Stage stage;
	
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private Button buttonDownLoadLocation;
	
	@FXML
	private Label labelDownloadLocation;
	
	public PreferencesDialogController(BudgetPreferences prefs) {
		this.prefs = prefs;
	}
	
	@FXML
	public void initialize() {
		this.stage = new Stage();
		stage.setScene(new Scene(anchorPane));
		stage.setTitle("Voorkeursinstellingen");
	}
	
	public void show() {
		labelDownloadLocation.setText(prefs.getDownloadLocation().toString());

		stage.show();
	}
	
	@FXML
	private void changeDownloadLocation(ActionEvent event) {
		
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Selecteer de download lokatie");
		File selectedDirectory = directoryChooser.showDialog(stage);
		if (selectedDirectory != null) {
			labelDownloadLocation.setText(selectedDirectory.getAbsolutePath());
			prefs.setDownloadLocation(selectedDirectory.getAbsolutePath());
		}
	}
}
