package nl.budget.view;

import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import nl.budget.view.configuration.ConfigurationDialog;
import nl.budget.view.post.PostView;
import nl.budget.view.transaction.TransactionView;

@Component
public class RootView {

	private BorderPane rootWindow;
	private MenuBar menuBar;
	private final TransactionView transactionView;
	private final PostView postView;

	public RootView(TransactionView transactionView, PostView postView) {

		this.transactionView = transactionView;
		this.postView = postView;

		transactionView.initRootView(this);

		initializeMenu();
		initializeRootWindow();
		
	}

	public Parent asParent() {
		return rootWindow;
	}

	public void showTransactionView(ActionEvent actionEvent) {
		rootWindow.setCenter(transactionView.getView());
	}
	
	public void showPostView(ActionEvent actionEvent) {
		rootWindow.setCenter(postView.getView());
	}
	
	public void showConfigurationDialog(ActionEvent actionEvent) {
		ConfigurationDialog configurationDialog = new ConfigurationDialog();
		configurationDialog.showConfigurationDialog();
	}
	
	private void initializeRootWindow() {
		rootWindow = new BorderPane();
		rootWindow.setTop(menuBar);
	}

	private void initializeMenu() {
		menuBar = new MenuBar();
		
		Menu budgetMenu = new Menu(ViewConstant.BUDGET);
		MenuItem configMenuItem = new MenuItem(ViewConstant.CONFIGURATION);
		configMenuItem.setOnAction(this::showConfigurationDialog);
		SeparatorMenuItem separator = new SeparatorMenuItem();
		MenuItem exitMenuItem = new MenuItem(ViewConstant.CLOSE_PROGRAM);
		exitMenuItem.setOnAction((ActionEvent e) -> System.exit(0));
		
		budgetMenu.getItems().addAll(configMenuItem, separator, exitMenuItem);
		
		Menu transactionsMenu = new Menu(ViewConstant.TRANSACTIONS);
		MenuItem transactionOverviewMenuItem = new MenuItem(ViewConstant.TRANSACTIONS_OVERVIEW);
		transactionOverviewMenuItem.setOnAction(this::showTransactionView);
		MenuItem searchTransactionsMenuItem = new MenuItem(ViewConstant.SEARCH_NEW_TRANSACTIONS);
		searchTransactionsMenuItem.setOnAction(e -> transactionView.searchNewTransactions());
		transactionsMenu.getItems().addAll(transactionOverviewMenuItem, searchTransactionsMenuItem);
		
		menuBar.getMenus().addAll(budgetMenu, transactionsMenu);

	}
}
