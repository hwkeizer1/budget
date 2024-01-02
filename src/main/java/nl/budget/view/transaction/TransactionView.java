package nl.budget.view.transaction;

import org.springframework.stereotype.Component;

import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nl.budget.model.Transaction;
import nl.budget.service.TransactionService;
import nl.budget.view.AbstractView;

@Component
public class TransactionView extends AbstractView {

	private final TransactionService transactionService;
	private VBox mainView;
	private TableView<Transaction> transactionTableView;
	
	public TransactionView(TransactionService transactionService) {
		this.transactionService = transactionService;
		initComponents();
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
		// TODO: replace dummy content
		transactionTableView = new TableView<>();
	}

}
