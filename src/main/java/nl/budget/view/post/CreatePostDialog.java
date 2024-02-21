package nl.budget.view.post;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import nl.budget.model.Account;
import nl.budget.model.Post;
import nl.budget.service.AccountService;
import nl.budget.service.PostService;
import nl.budget.view.ViewCSS;
import nl.budget.view.ViewConstant;
import nl.budget.view.ViewMessage;
import nl.budget.view.util.converter.CurrencyConverter;
import nl.budget.view.util.filter.CurrencyFilter;

@Component
public class CreatePostDialog {

	private final PostService postService;
	private final AccountService accountService;

	private Stage stage;
	private TextField categoryTextField;
	private Label categoryValidation;
	private TextField budgetTextField;
	private Label budgetValidation;
	private ComboBox<Account> accountComboBox;
	private Label accountValidation;
	private Label alreadyExistsValidation;

	private Post post;

	public CreatePostDialog(PostService postService, AccountService accountService) {
		this.postService = postService;
		this.accountService = accountService;
	}

	public void showAndWait(Window parent) {
		post = new Post();

		VBox vbox = new VBox();
		Scene scene = new Scene(vbox);
		scene.getStylesheets().addAll(getClass().getResource(ViewCSS.DIALOG_RESOURCE).toExternalForm());
		stage = new Stage();
		stage.initOwner(parent);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setTitle(ViewConstant.DIALOG_ADD_POST);
		stage.setScene(scene);
		vbox.getChildren().addAll(createForm(), createButtonBar());
		stage.showAndWait();
	}

	private void validateInput() {
		boolean isValid = true;
		if (categoryTextField.getText() == null) {
			categoryValidation.setText(ViewMessage.CATEGORY_REQUIRED);
			categoryValidation.setVisible(true);
			isValid &= false;
		}
		if (budgetTextField.getText() == null || budgetTextField.getText().isEmpty()) {
			budgetValidation.setText(ViewMessage.BUDGET_REQUIRED);
			budgetValidation.setVisible(true);
			isValid &= false;
		}
		if (accountComboBox.getValue() == null) {
			accountValidation.setText(ViewMessage.ACCOUNT_REQUIRED);
			accountValidation.setVisible(true);
			isValid &= false;
		}
		if (postService.postAlreadyExistsForThisPeriod(post)) {
			alreadyExistsValidation.setText(ViewMessage.CATEGORY_ALREADY_EXISTS);
			alreadyExistsValidation.setVisible(true);
			isValid &= false;
		}
		if (isValid) {
			postService.save(post);
			stage.close();
		}
	}

	private Node createForm() {

		GridPane inputForm = new GridPane();
		GridPane.setHgrow(inputForm, Priority.ALWAYS);
		GridPane.setVgrow(inputForm, Priority.ALWAYS);

		inputForm.setPadding(new Insets(15));
		inputForm.setHgap(15);

		Label categoryLabel = new Label(ViewConstant.POSTS_CATEGORY);
		inputForm.add(categoryLabel, 0, 0);
		categoryTextField = new TextField();
		categoryTextField.setMinWidth(250);
		categoryTextField.focusedProperty()
				.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
					if (newValue) {
						categoryValidation.setVisible(false);
						alreadyExistsValidation.setVisible(false);
					}
				});
		categoryTextField.textProperty().bindBidirectional(post.categoryProperty());
		inputForm.add(categoryTextField, 1, 0);
		categoryValidation = new Label();
		categoryValidation.getStyleClass().add(ViewCSS.VALIDATION);
		categoryValidation.setVisible(false);
		inputForm.add(categoryValidation, 1, 1);

		Label startBalanceLabel = new Label(ViewConstant.START_BALANCE_LABEL);
		inputForm.add(startBalanceLabel, 0, 2);
		TextField startBalanceTextField = new TextField();
		startBalanceTextField.setMinWidth(250);
		inputForm.add(startBalanceTextField, 1, 2);
		Label dummyValidation = new Label();
		dummyValidation.getStyleClass().add(ViewCSS.VALIDATION);
		inputForm.add(dummyValidation, 1, 3);
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		TextFormatter<BigDecimal> startBalanceFormatter = new TextFormatter(new CurrencyConverter(), 0,
				new CurrencyFilter());
		startBalanceFormatter.valueProperty().bindBidirectional(post.startBalanceProperty());
		startBalanceTextField.setTextFormatter(startBalanceFormatter);

		Label budgetLabel = new Label(ViewConstant.BUDGET_LABEL);
		inputForm.add(budgetLabel, 0, 4);
		budgetTextField = new TextField();
		budgetTextField.setMinWidth(250);
		budgetTextField.focusedProperty()
				.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
					if (newValue) {
						budgetValidation.setVisible(false);
					}
				});
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		TextFormatter<BigDecimal> budgetFormatter = new TextFormatter(new CurrencyConverter(), 0,
				new CurrencyFilter());
		budgetFormatter.valueProperty().bindBidirectional(post.budgetProperty());
		budgetTextField.setTextFormatter(budgetFormatter);
		inputForm.add(budgetTextField, 1, 4);
		budgetValidation = new Label();
		budgetValidation.getStyleClass().add(ViewCSS.VALIDATION);
		budgetValidation.setVisible(false);
		inputForm.add(budgetValidation, 1, 5);

		Label accountLabel = new Label(ViewConstant.ACCOUNT_LABEL);
		inputForm.add(accountLabel, 0, 6);
		accountComboBox = new ComboBox<>();
		accountComboBox.setMinWidth(250);
		accountComboBox.getItems().setAll(accountService.getObservableList());
		accountComboBox.focusedProperty()
				.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
					if (newValue) {
						accountValidation.setVisible(false);
					}
				});
		accountComboBox.valueProperty().bindBidirectional(post.accountProperty());
		inputForm.add(accountComboBox, 1, 6);
		accountValidation = new Label();
		accountValidation.getStyleClass().add(ViewCSS.VALIDATION);
		accountValidation.setVisible(false);
		inputForm.add(accountValidation, 1, 7);
		
		alreadyExistsValidation = new Label();
		alreadyExistsValidation.getStyleClass().add(ViewCSS.VALIDATION);
		alreadyExistsValidation.setVisible(false);
		inputForm.add(alreadyExistsValidation, 1, 8);

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
