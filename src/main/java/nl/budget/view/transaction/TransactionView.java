package nl.budget.view.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nl.budget.model.Transaction;
import nl.budget.service.transaction.TransactionService;
import nl.budget.view.AbstractView;
import nl.budget.view.ViewConstant;

@Component
public class TransactionView extends AbstractView {

	private final TransactionService transactionService;
	private VBox mainView;
	private TableView<Transaction> transactionTableView;
	
	public TransactionView(TransactionService transactionService) {
		this.transactionService = transactionService;
		initComponents();
	}
	
	public void searchNewTransactions() {
		transactionService.searchNewTransactions();
	}

	@Override
	public void initComponents() {
		
		mainView = new VBox();
		createTransactionTableView();
		mainView.getChildren().add(transactionTableView);
	}

	@Override
	public Pane getView() {
		return mainView;
	}
	
	private void createTransactionTableView() {
		
		transactionTableView = new TableView<>();
		transactionTableView.setItems(transactionService.getObservableList());
		
		TableColumn<Transaction, LocalDate> journalDateColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_DATE);
		TableColumn<Transaction, Number> numberColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_NUMBER);
		TableColumn<Transaction, String> accountColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_ACCOUNT);
		TableColumn<Transaction, String> contraAccountColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_CONTRA_ACCOUNT);
		TableColumn<Transaction, BigDecimal> balanceColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_BALANCE);
		TableColumn<Transaction, String> currencyTypeColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_CURRENCY_TYPE);
		TableColumn<Transaction, BigDecimal> amountColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_AMOUNT);
		TableColumn<Transaction, String> descriptionColumn = new TableColumn<>(ViewConstant.TRANSACTIONS_DESCRIPTION);
		
		journalDateColumn.setCellValueFactory(cellData -> cellData.getValue().journalDateProperty());
		numberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
		accountColumn.setCellValueFactory(cellData -> cellData.getValue().accountProperty().get().ibanProperty());
		contraAccountColumn.setCellValueFactory(cellData -> cellData.getValue().contraAccountProperty());
		balanceColumn.setCellValueFactory(cellData -> cellData.getValue().balanceProperty());
		currencyTypeColumn.setCellValueFactory(cellData -> cellData.getValue().currencyTypeProperty());
		amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
		descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		
		transactionTableView.getColumns().add(journalDateColumn);
		transactionTableView.getColumns().add(numberColumn);
		transactionTableView.getColumns().add(accountColumn);
		transactionTableView.getColumns().add(contraAccountColumn);
		transactionTableView.getColumns().add(balanceColumn);
		transactionTableView.getColumns().add(currencyTypeColumn);
		transactionTableView.getColumns().add(amountColumn);
		transactionTableView.getColumns().add(descriptionColumn);
	}

}
