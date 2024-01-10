package nl.budget.view.account;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import nl.budget.model.Account;
import nl.budget.view.ViewConstant;

public class CreateAccountDialog extends Dialog<Account> {

	private TextField ibanTextField;
	private TextField accountHolderTextField;
	private TextField descriptionTextField;

	private ButtonType apply = new ButtonType("OK", ButtonData.OK_DONE);
	private ButtonType cancel = new ButtonType("Annuleren", ButtonData.CANCEL_CLOSE);

	public CreateAccountDialog() {

		Platform.runLater(() -> {
			setResizable(true);
			setTitle(ViewConstant.DIALOG_ADD_ACCOUNT);

			DialogPane dialogPane = new DialogPane();
			dialogPane.setContent(initializeForm());
			dialogPane.getButtonTypes().addAll(apply, cancel);
			setDialogPane(dialogPane);

			setResultConverter(button -> {
				Account account = new Account();
				account.setIban(ibanTextField.getText());
				account.setAccountHolder(accountHolderTextField.getText());
				account.setDescription(descriptionTextField.getText());
				return account;
			});
		});
	}

	private Node initializeForm() {

		GridPane inputForm = new GridPane();
		GridPane.setHgrow(inputForm, Priority.ALWAYS);
		GridPane.setVgrow(inputForm, Priority.ALWAYS);

		inputForm.setPadding(new Insets(15));
		inputForm.setHgap(15);
		inputForm.setVgap(10);

		Label ibanLabel = new Label(ViewConstant.IBAN_LABEL);
		inputForm.add(ibanLabel, 0, 0);
		ibanTextField = new TextField("IBAN");
		ibanTextField.setMinWidth(250);
		inputForm.add(ibanTextField, 1, 0);

		Label accountHolderLabel = new Label(ViewConstant.ACCOUNT_HOLDER_LABEL);
		inputForm.add(accountHolderLabel, 0, 1);
		accountHolderTextField = new TextField();
		accountHolderTextField.setMinWidth(250);
		inputForm.add(accountHolderTextField, 1, 1);

		Label descriptionLabel = new Label(ViewConstant.DESCRIPTION_LABEL);
		inputForm.add(descriptionLabel, 0, 2);
		descriptionTextField = new TextField();
		descriptionTextField.setMinWidth(250);
		inputForm.add(descriptionTextField, 1, 2);

		return inputForm;
	}

}
