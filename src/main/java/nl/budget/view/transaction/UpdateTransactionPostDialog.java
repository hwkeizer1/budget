package nl.budget.view.transaction;

import java.util.List;

import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import nl.budget.model.Post;
import nl.budget.model.Transaction;
import nl.budget.service.PostService;
import nl.budget.service.transaction.TransactionService;
import nl.budget.view.ViewCSS;
import nl.budget.view.ViewConstant;
import nl.budget.view.util.Util;
import nl.garvelink.iban.IBAN;

@Component
public class UpdateTransactionPostDialog {
	
	private Stage stage;
	private final TransactionService transactionService;
	private final PostService postService;

	private Transaction transaction;
	ComboBox<Post> postComboBox;
	
	public UpdateTransactionPostDialog(TransactionService transactionService, PostService postService) {
		this.transactionService = transactionService;
		this.postService = postService;
	}

	public void showAndWait(Window parent) {
		List<Transaction> transactions = transactionService.findTransactionsWithoutPost();
		if (transactions.size() > 0) {
			showAndWait(parent, transactions.getFirst());
		}
	}
	
	public void showAndWait(Window parent, Transaction transaction) {
		this.transaction = transaction;
		VBox vbox = new VBox();
		Scene scene = new Scene(vbox);
		scene.getStylesheets().addAll(getClass().getResource(ViewCSS.DIALOG_RESOURCE).toExternalForm());
		stage = new Stage();
		stage.initOwner(parent);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setTitle(ViewConstant.DIALOG_UPDATE_TRANSACTION_POST);
		stage.setScene(scene);
		vbox.getChildren().addAll(createForm(transaction), createButtonBar());
		stage.showAndWait();
	}

	private Node createForm(Transaction transaction) {

		GridPane inputForm = new GridPane();
		inputForm.setMinWidth(550);
		inputForm.setPrefWidth(550);
		inputForm.setMaxWidth(550);
		GridPane.setHgrow(inputForm, Priority.ALWAYS);
		GridPane.setVgrow(inputForm, Priority.ALWAYS);

		inputForm.setPadding(new Insets(15));
		inputForm.setHgap(15);
		inputForm.setVgap(5);

		Label postLabel = new Label(ViewConstant.TRANSACTIONS_POST);
		inputForm.add(postLabel, 0, 0);
		postComboBox = new ComboBox<>();
		postComboBox.setMinWidth(250);
		postComboBox.getItems().setAll(postService.getObservableList());
		inputForm.add(postComboBox, 1, 0);

		Label transactionDateLabel = new Label(ViewConstant.TRANSACTIONS_DATE);
		transactionDateLabel.setMinWidth(100);
		inputForm.add(transactionDateLabel, 0, 2);
		Label transactionDateValue = new Label(transaction.getJournalDate().toString());
		inputForm.add(transactionDateValue, 1, 2);
		
		Label transactionNumberLabel = new Label(ViewConstant.TRANSACTIONS_NUMBER);
		inputForm.add(transactionNumberLabel, 0, 3);
		Label transactionNumberValue = new Label(transaction.getNumber().toString());
		inputForm.add(transactionNumberValue, 1, 3);
		
		Label transactionAccountLabel = new Label(ViewConstant.TRANSACTIONS_ACCOUNT);
		inputForm.add(transactionAccountLabel, 0, 4);
		Label transactionAccountValue = new Label(IBAN.toPretty(transaction.getAccount().getIban()));
		inputForm.add(transactionAccountValue, 1, 4);
		
		Label transactionContraAccountLabel = new Label(ViewConstant.TRANSACTIONS_CONTRA_ACCOUNT);
		inputForm.add(transactionContraAccountLabel, 0, 5);
		Label transactionContraAccountValue = new Label(transaction.getContraAccount() == null ? "" :transaction.getContraAccount());
		inputForm.add(transactionContraAccountValue, 1, 5);
		
		Label transactionBalanceLabel = new Label(ViewConstant.TRANSACTIONS_BALANCE);
		inputForm.add(transactionBalanceLabel, 0, 6);
		Label transactionBalanceValue = new Label(transaction.getBalance().toString());
		Util.styleBigDecimalNode(transaction.getBalance(), transactionBalanceValue);
		inputForm.add(transactionBalanceValue, 1, 6);
		
		Label transactionCurrencyTypeLabel = new Label(ViewConstant.TRANSACTIONS_CURRENCY_TYPE);
		inputForm.add(transactionCurrencyTypeLabel, 0, 7);
		Label transactionCurrencyTypeValue = new Label(transaction.getCurrencyType());
		inputForm.add(transactionCurrencyTypeValue, 1, 7);
		
		Label transactionAmountLabel = new Label(ViewConstant.TRANSACTIONS_AMOUNT);
		inputForm.add(transactionAmountLabel, 0, 8);
		Label transactionAmountValue = new Label(transaction.getAmount().toString());
		Util.styleBigDecimalNode(transaction.getAmount(), transactionAmountValue);
		inputForm.add(transactionAmountValue, 1, 8);
		
		Label transactionDescriptionLabel = new Label(ViewConstant.TRANSACTIONS_DESCRIPTION);
		inputForm.add(transactionDescriptionLabel, 0, 9);
		Label transactionDescriptionValue = new Label(transaction.getDescription());
		transactionDescriptionValue.setWrapText(true);
		inputForm.add(transactionDescriptionValue, 1, 9);
		
		return inputForm;
	}

	private Node createButtonBar() {

		Button apply = new Button(ViewConstant.OK_BUTTON_TEXT);
		apply.setOnAction(this::updateTransaction);
		Button cancel = new Button(ViewConstant.CANCEL_BUTTON_TEXT);
		cancel.setOnAction(e -> stage.close());

		ButtonBar buttonBar = new ButtonBar();
		buttonBar.setPadding(new Insets(0, 15, 15, 15));
		buttonBar.getButtons().addAll(apply, cancel);

		return buttonBar;
	}
	
	private void updateTransaction(ActionEvent event) {
		transaction.setPost(postComboBox.getValue());
		transactionService.update(transaction);
		stage.close();
	}
}
