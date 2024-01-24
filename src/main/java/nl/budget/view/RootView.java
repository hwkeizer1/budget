package nl.budget.view;

import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import nl.budget.view.account.CreateAccountDialog;
import nl.budget.view.configuration.ConfigurationDialog;
import nl.budget.view.post.PostView;
import nl.budget.view.transaction.TransactionView;

@Component
public class RootView {

	private BorderPane rootWindow;
	private MenuBar menuBar;
	private final TransactionView transactionView;
	private final CreateAccountDialog createAccountDialog;
	private final PostView postView;

	public RootView(TransactionView transactionView, CreateAccountDialog createAccountDialog, PostView postView) {
		this.transactionView = transactionView;
		this.createAccountDialog = createAccountDialog;
		this.postView = postView;
		transactionView.initRootView(this);

		initializeMenu();
		initializeRootWindow();

	}

	public Parent asParent() {
		return rootWindow;
	}
	
	public Window getWindow() {
		return rootWindow.getScene().getWindow();
	}
	
	public void handleAccountOverviewMenuItem(ActionEvent actionEvent) {
		// TODO decide if needed
		// rootWindow.setCenter(accountView.getView());
	}
	
	public void handleCreateAccountMenuItem(ActionEvent actionEvent) {
		createAccountDialog.showAndWait(getWindow());	
	}

	public void handleTransactionOverviewMenuItem(ActionEvent actionEvent) {
		rootWindow.setCenter(transactionView.getView());
	}
	
	public void handleSearchTransactionsMenuItem(ActionEvent event) {
		rootWindow.setCenter(transactionView.getView());
		transactionView.searchNewTransactions();
	}

	public void handlePostOverviewMenuItem(ActionEvent actionEvent) {
		rootWindow.setCenter(postView.getView());
	}
	
	public void handleEditPostsMenuItem(ActionEvent event) {

	}

	public void handleConfigMenuItem(ActionEvent actionEvent) {
		ConfigurationDialog configurationDialog = new ConfigurationDialog();
		configurationDialog.showConfigurationDialog();
	}
	
	public void handleExitMenuItem(ActionEvent actionEvent) {
		System.exit(0);
	}

	private void initializeRootWindow() {
		rootWindow = new BorderPane();
		rootWindow.setTop(menuBar);
	}

	private void initializeMenu() {
		menuBar = new MenuBar();

		Menu budgetMenu = new Menu(ViewConstant.BUDGET);

		MenuItem configMenuItem = new MenuItem(ViewConstant.CONFIGURATION);
		configMenuItem.setOnAction(this::handleConfigMenuItem);
		SeparatorMenuItem separator = new SeparatorMenuItem();
		MenuItem exitMenuItem = new MenuItem(ViewConstant.CLOSE_PROGRAM);
		exitMenuItem.setOnAction(this::handleExitMenuItem);

		budgetMenu.getItems().addAll(configMenuItem, separator, exitMenuItem);

		Menu accountMenu = new Menu(ViewConstant.ACCOUNTS);
		MenuItem accountOverviewMenuItem = new MenuItem(ViewConstant.ACCOUNTS_OVERVIEW);
		accountOverviewMenuItem.setOnAction(this::handleAccountOverviewMenuItem);
		MenuItem createAccountMenuItem = new MenuItem(ViewConstant.NEW_ACCOUNT);
		createAccountMenuItem.setOnAction(this::handleCreateAccountMenuItem);
		
		accountMenu.getItems().addAll(accountOverviewMenuItem, createAccountMenuItem);
		
		Menu transactionsMenu = new Menu(ViewConstant.TRANSACTIONS);
		MenuItem transactionOverviewMenuItem = new MenuItem(ViewConstant.TRANSACTIONS_OVERVIEW);
		transactionOverviewMenuItem.setOnAction(this::handleTransactionOverviewMenuItem);
		MenuItem searchTransactionsMenuItem = new MenuItem(ViewConstant.SEARCH_NEW_TRANSACTIONS);
		searchTransactionsMenuItem.setOnAction(this::handleSearchTransactionsMenuItem);
		
		transactionsMenu.getItems().addAll(transactionOverviewMenuItem, searchTransactionsMenuItem);
		
		Menu postMenu = new Menu(ViewConstant.POSTS);
		MenuItem postOverviewMenuItem = new MenuItem(ViewConstant.POSTS_OVERVIEW);
		postOverviewMenuItem.setOnAction(this::handlePostOverviewMenuItem);
		MenuItem editPostsMenuItem = new MenuItem(ViewConstant.EDIT_POSTS);
		editPostsMenuItem.setOnAction(this::handleEditPostsMenuItem);
		
		postMenu.getItems().addAll(postOverviewMenuItem, editPostsMenuItem);

		menuBar.getMenus().addAll(budgetMenu, accountMenu, transactionsMenu, postMenu);

	}
}
