package nl.budget.view.configuration;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.budget.service.ConfigService;
import nl.budget.view.ViewConstant;

public class ConfigurationDialog {

	private Stage dialog;
	private TextField backupFolderTextField;
	private TextField downloadFolderTextField;

	public void showConfigurationDialog() {

		dialog = new Stage();
		dialog.setTitle(ViewConstant.DIALOG_CHANGE_CONFIGURATION);

		VBox layout = new VBox(10);
		layout.getChildren().addAll(initializeForm(), initializeAccountOverview(), createButtonBar());
		layout.setPadding(new Insets(15));

		dialog.setScene(new Scene(layout));
		dialog.centerOnScreen();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.showAndWait();
	}
	
	private Node initializeAccountOverview() {
		VBox vbox = new VBox();
		vbox.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, null, null)));
		return vbox;
	}

	private Node initializeForm() {   
		GridPane inputForm = new GridPane();
		inputForm.setPadding(new Insets(15));
		inputForm.setHgap(15);
		inputForm.setVgap(10);

		Button selectBackupFolderButton = new Button(ViewConstant.ELLIPSIS);
	    selectBackupFolderButton.setOnAction(this::selectBackupFolder);
	    Label backupFolderLabel = new Label(ViewConstant.BACKUP_FOLDER_LABEL);
	    inputForm.add(backupFolderLabel, 0, 0);
	    backupFolderTextField = new TextField(ConfigService.getConfigProperty(ViewConstant.BACKUP_FOLDER_PROP));
	    backupFolderTextField.setMinWidth(250);
	    inputForm.add(backupFolderTextField, 1, 0);
	    inputForm.add(selectBackupFolderButton, 2, 0);
	    
	    Button selectDownloadFolderButton = new Button(ViewConstant.ELLIPSIS);
	    selectDownloadFolderButton.setOnAction(this::selectDownloadFolder);
	    Label downloadFolderLabel = new Label(ViewConstant.DOWNLOAD_FOLDER_LABEL);
	    inputForm.add(downloadFolderLabel, 0, 1);
	    downloadFolderTextField =  new TextField(ConfigService.getConfigProperty(ViewConstant.DOWNLOAD_FOLDER_PROP));
	    inputForm.add(downloadFolderTextField, 1, 1);
	    inputForm.add(selectDownloadFolderButton, 2, 1);

		return inputForm;
	}

	private ButtonBar createButtonBar() {
		ButtonBar buttonBar = new ButtonBar();

		Button okButton = new Button(ViewConstant.OK_BUTTON_TEXT);
		ButtonBar.setButtonData(okButton, ButtonData.OK_DONE);
		okButton.setOnAction(this::save);

		Button cancelButton = new Button(ViewConstant.CANCEL_BUTTON_TEXT);
		ButtonBar.setButtonData(cancelButton, ButtonData.CANCEL_CLOSE);
		cancelButton.setOnAction(e -> dialog.close());

		buttonBar.getButtons().addAll(okButton, cancelButton);
		return buttonBar;
	}

	private void selectBackupFolder(ActionEvent actionEvent) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setInitialDirectory(new File(ConfigService.getConfigProperty(ViewConstant.BACKUP_FOLDER_PROP)));
		File directory = directoryChooser.showDialog(dialog);
		if (directory != null) {
			backupFolderTextField.setText(directory.getAbsolutePath());
		}
	}
	
	private void selectDownloadFolder(ActionEvent actionEvent) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setInitialDirectory(new File(ConfigService.getConfigProperty(ViewConstant.DOWNLOAD_FOLDER_PROP)));
		File directory = directoryChooser.showDialog(dialog);
		if (directory != null) {
			downloadFolderTextField.setText(directory.getAbsolutePath());
		}
	}

	private void save(ActionEvent actionEvent) {
		ConfigService.setConfigProperty(ViewConstant.BACKUP_FOLDER_PROP, backupFolderTextField.getText());
		ConfigService.setConfigProperty(ViewConstant.DOWNLOAD_FOLDER_PROP, downloadFolderTextField.getText());
		dialog.close();
	}
}
