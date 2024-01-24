package nl.budget.view.account;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.extern.slf4j.Slf4j;
import nl.budget.model.Account;
import nl.budget.service.AccountService;
import nl.budget.view.ViewCSS;
import nl.budget.view.ViewConstant;
import nl.budget.view.ViewMessage;
import nl.budget.view.util.converter.CurrencyConverter;
import nl.budget.view.util.converter.CurrencyFilter;

@Slf4j
@Component
public class CreateAccountDialog {

	private final AccountService accountService;

	private Stage stage;
	private TextField ibanTextField;
	private Label ibanValidation;
	private TextField accountHolderTextField;
	private Label accountHolderValidation;
	private TextField descriptionTextField;
	private Label descriptionValidation;
	private TextField balanceTextField;
	private Label balanceValidation;

	private Account account;

	public CreateAccountDialog(AccountService accountService) {
		this.accountService = accountService;
	}

	public void showAndWait(Window parent) {
		account = new Account();

		VBox vbox = new VBox();
		Scene scene = new Scene(vbox);
		scene.getStylesheets().addAll(getClass().getResource(ViewCSS.DIALOG_RESOURCE).toExternalForm());
		stage = new Stage();
		stage.initOwner(parent);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setTitle(ViewConstant.DIALOG_ADD_ACCOUNT);
		stage.setScene(scene);
		vbox.getChildren().addAll(createForm(), createButtonBar());
		stage.showAndWait();
	}

	private void validateInput() {
		boolean isValid = true;
		if (!accountService.isValidNewIban(ibanTextField.getText())) {
			ibanValidation.setText(ViewMessage.IBAN_NUMBER_INCORRECT);
			ibanValidation.setVisible(true);
			isValid &= false;
		}
		if (accountHolderTextField.getText().isEmpty()) {
			accountHolderValidation.setText(ViewMessage.ACCOUNT_HOLDER_REQUIRED);
			accountHolderValidation.setVisible(true);
			isValid &= false;
		}
		if (descriptionTextField.getText().isEmpty()) {
			descriptionValidation.setText(ViewMessage.DESCRIPTION_REQUIRED);
			descriptionValidation.setVisible(true);
			isValid &= false;
		}
		if (balanceTextField.getText().isEmpty()) {
			balanceValidation.setText(ViewMessage.BALANCE_REQUIRED);
			balanceValidation.setVisible(true);
			isValid &= false;
		}
		if (isValid) {
			account.setIban(ibanTextField.getText());
			account.setAccountHolder(accountHolderTextField.getText());
			account.setDescription(descriptionTextField.getText());
			log.debug("ACCOUNT: {}", account);
			accountService.save(account);
			stage.close();
		}
	}

	private Node createForm() {

		GridPane inputForm = new GridPane();
		GridPane.setHgrow(inputForm, Priority.ALWAYS);
		GridPane.setVgrow(inputForm, Priority.ALWAYS);

		inputForm.setPadding(new Insets(15));
		inputForm.setHgap(15);

		Label ibanLabel = new Label(ViewConstant.IBAN_LABEL);
		inputForm.add(ibanLabel, 0, 0);
		ibanTextField = new TextField();
		ibanTextField.setMinWidth(250);
		ibanTextField.focusedProperty()
				.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
					if (newValue) {
						ibanValidation.setVisible(false);
					}
				});
		inputForm.add(ibanTextField, 1, 0);
		ibanValidation = new Label();
		ibanValidation.getStyleClass().add(ViewCSS.VALIDATION);
		ibanValidation.setVisible(false);
		inputForm.add(ibanValidation, 1, 1);

		Label accountHolderLabel = new Label(ViewConstant.ACCOUNT_HOLDER_LABEL);
		inputForm.add(accountHolderLabel, 0, 2);
		accountHolderTextField = new TextField();
		accountHolderTextField.setMinWidth(250);
		accountHolderTextField.focusedProperty()
				.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
					if (newValue) {
						accountHolderValidation.setVisible(false);
					}
				});
		inputForm.add(accountHolderTextField, 1, 2);
		accountHolderValidation = new Label();
		accountHolderValidation.getStyleClass().add(ViewCSS.VALIDATION);
		accountHolderValidation.setVisible(false);
		inputForm.add(accountHolderValidation, 1, 3);

		Label descriptionLabel = new Label(ViewConstant.DESCRIPTION_LABEL);
		inputForm.add(descriptionLabel, 0, 4);
		descriptionTextField = new TextField();
		descriptionTextField.setMinWidth(250);
		descriptionTextField.focusedProperty()
				.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
					if (newValue) {
						descriptionValidation.setVisible(false);
					}
				});
		inputForm.add(descriptionTextField, 1, 4);
		descriptionValidation = new Label();
		descriptionValidation.getStyleClass().add(ViewCSS.VALIDATION);
		descriptionValidation.setVisible(false);
		inputForm.add(descriptionValidation, 1, 5);

		Label balanceLabel = new Label(ViewConstant.BALANCE_LABEL);
		inputForm.add(balanceLabel, 0, 6);
		balanceTextField = new TextField();
		balanceTextField.setMinWidth(250);
		balanceTextField.focusedProperty()
				.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
					if (newValue) {
						balanceValidation.setVisible(false);
					}
				});

		@SuppressWarnings({ "rawtypes", "unchecked" })
		TextFormatter<BigDecimal> currencyFormatter = new TextFormatter(new CurrencyConverter(), 0,
				new CurrencyFilter());
		currencyFormatter.valueProperty().bindBidirectional(account.balanceProperty());
		balanceTextField.setTextFormatter(currencyFormatter);

		inputForm.add(balanceTextField, 1, 6);
		balanceValidation = new Label();
		balanceValidation.getStyleClass().add(ViewCSS.VALIDATION);
		balanceValidation.setVisible(false);
		inputForm.add(balanceValidation, 1, 7);
		return inputForm;
	}

	private Node createButtonBar() {

		Button apply = new Button(ViewConstant.OK_BUTTON_TEXT);
		apply.setOnAction(e -> validateInput());
		Button cancel = new Button(ViewConstant.CANCEL_BUTTON_TEXT);
		cancel.setOnAction(e -> stage.close());

		ButtonBar buttonBar = new ButtonBar();
		buttonBar.setPadding(new Insets(0, 15, 15, 15));
		buttonBar.getButtons().addAll(apply, cancel);

		return buttonBar;
	}
}
