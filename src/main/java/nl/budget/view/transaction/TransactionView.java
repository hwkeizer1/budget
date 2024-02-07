package nl.budget.view.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import nl.budget.model.Account;
import nl.budget.model.Transaction;
import nl.budget.service.AccountService;
import nl.budget.service.transaction.TransactionService;
import nl.budget.view.AbstractView;
import nl.budget.view.ViewConstant;

@Component
public class TransactionView extends AbstractView {

	private final TransactionService transactionService;
	private final AccountService accountService;
	private final UpdateTransactionPostDialog addPostToTransactionDialog;
	
	private VBox mainView;
	private TableView<Transaction> transactionTableView;
	
	public TransactionView(TransactionService transactionService, AccountService accountService,
			UpdateTransactionPostDialog addPostToTransactionDialog) {
		this.transactionService = transactionService;
		this.accountService = accountService;
		this.addPostToTransactionDialog = addPostToTransactionDialog;
		initComponents();
	}
	
	public void searchNewTransactions() {
		List<Account> accounts = accountService.getList();
		List<Transaction> newTransactions = transactionService.searchNewTransactions(accounts);
		// TODO: Insert the automatically assignment of posts here
		addPostToNewTransactions(newTransactions);
	}
	
	// TODO: this method should also be called when (right-?) clicking a transaction to add or update a single post
	// 		 and also with menu option 'assign all not assigned transactions' (or something like that)
	public void addPostToNewTransactions(List<Transaction> newTransactions) {
		for (Transaction transaction : newTransactions) {
			addPostToTransactionDialog.showAndWait(getWindow(), transaction);
		}
	}

	@Override
	public void initComponents() {
		
		mainView = new VBox();
		createTransactionTableView();
		mainView.getChildren().addAll(createTransactionTableView());
	}

	@Override
	public Pane getView() {
		return mainView;
	}
	
	private TableView<Transaction> createTransactionTableView() {
		
		transactionTableView = new TableView<>();
		transactionTableView.setPrefHeight(2000); // TODO: For now oversize so all rows are visible, solve with properties later
		transactionTableView.setItems(transactionService.getObservableList());
		
		TableColumn<Transaction, String> categoryColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_POST);
		TableColumn<Transaction, LocalDate> journalDateColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_DATE);
		TableColumn<Transaction, Number> numberColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_NUMBER);
		TableColumn<Transaction, String> accountColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_ACCOUNT);
		TableColumn<Transaction, String> contraAccountColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_CONTRA_ACCOUNT);
		TableColumn<Transaction, BigDecimal> balanceColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_BALANCE);
		TableColumn<Transaction, String> currencyTypeColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_CURRENCY_TYPE);
		TableColumn<Transaction, BigDecimal> amountColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_AMOUNT);
		TableColumn<Transaction, String> descriptionColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_DESCRIPTION);
		

		
		categoryColumn.setCellValueFactory(c -> c.getValue().postProperty().get() != null ? c.getValue().postProperty().get().categoryProperty() : null);
		journalDateColumn.setCellValueFactory(cellData -> cellData.getValue().journalDateProperty());
		numberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
		accountColumn.setCellValueFactory(cellData -> cellData.getValue().accountProperty().get().ibanProperty());
		contraAccountColumn.setCellValueFactory(cellData -> cellData.getValue().contraAccountProperty());
		balanceColumn.setCellValueFactory(cellData -> cellData.getValue().balanceProperty());
		currencyTypeColumn.setCellValueFactory(cellData -> cellData.getValue().currencyTypeProperty());
		amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
		descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		
		transactionTableView.getColumns().add(categoryColumn);
		transactionTableView.getColumns().add(journalDateColumn);
		transactionTableView.getColumns().add(numberColumn);
		transactionTableView.getColumns().add(accountColumn);
		transactionTableView.getColumns().add(contraAccountColumn);
		transactionTableView.getColumns().add(balanceColumn);
		transactionTableView.getColumns().add(currencyTypeColumn);
		transactionTableView.getColumns().add(amountColumn);
		transactionTableView.getColumns().add(descriptionColumn);
		
		return transactionTableView;
	}
	
	private Window getWindow() {
		return mainView.getScene().getWindow();
	}
}
