package nl.budget.controllers;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxmlView;

@Slf4j
@Controller
@FxmlView("createAdministrationDialog.fxml")
public class CreateAdministrationDialogController {

	private Stage stage;
	
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private Button buttonSelectLocation;
	
	@FXML
	private Label labelSelectLocation;
	
	@FXML
	private TextField textFieldName;
	
	@FXML
	private Button buttonCancel;
	
	@FXML
	private Button buttonCreate;
	
	@FXML
	public void initialize() {
		this.stage = new Stage();
		stage.setScene(new Scene(anchorPane));
		stage.setTitle("Maak een nieuwe administratie aan");
	}
	
	public void show() {
		stage.show();
	}
	
	@FXML
	private void selectLocation(ActionEvent event) {	
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Selecteer de download lokatie");
		File selectedDirectory = directoryChooser.showDialog(stage);
		if (selectedDirectory != null) {
			labelSelectLocation.setText(selectedDirectory.getAbsolutePath());
		}
	}
	
	@FXML
	private void cancelDialog() {
		stage.close();
	}
	
	@FXML
	private void createAdministration() {
		if (textFieldName.getText().isBlank()) {
			showErrorAlertInvalidName();
			return;
		}
		if (labelSelectLocation.getText().isBlank()) {
			showErrorAlertInvalidFolder();
			return;
		}
		Path locationPath = validateLocationPath(labelSelectLocation.getText(), textFieldName.getText());
		createFolderStructure(locationPath);
		stage.close();
		
		
	}
	
	private Path validateLocationPath(String folder, String name) {
		Path path = Paths.get(folder, name);
		return path;
	}
	
	private void createFolderStructure(Path path) {
		File rootFolder = path.toFile();
		if (!rootFolder.exists()) {
			rootFolder.mkdir();
			File configPath = Paths.get(rootFolder.getAbsolutePath(), "Configuration").toFile();
			configPath.mkdir();
		}
	}
	
	private void showErrorAlertInvalidName() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Ongeldige invoer");
		alert.setHeaderText("De nieuwe administratie moet een geldige naam hebben");
		alert.showAndWait();
	}
	
	private void showErrorAlertInvalidFolder() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Ongeldige invoer");
		alert.setHeaderText("De nieuwe administratie moet een geldige lokatie folder hebben");
		alert.showAndWait();
	}
}
