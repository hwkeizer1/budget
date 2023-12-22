package nl.budget.view;

import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
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

	private void initializeRootWindow() {
		rootWindow = new BorderPane();
		rootWindow.setTop(menuBar);
	}

	private void initializeMenu() {
		menuBar = new MenuBar();
		MenuItem transactionMenuItem = new MenuItem(ViewConstant.TRANSACTIONS);
		transactionMenuItem.setOnAction(this::showTransactionView);
		
		MenuItem postMenuItem = new MenuItem(ViewConstant.POSTS);
		postMenuItem.setOnAction(this::showPostView);
		
		
		Menu budget = new Menu(ViewConstant.BUDGET);
		budget.getItems().addAll(transactionMenuItem, postMenuItem);
		
		menuBar.getMenus().add(budget);
	}

	public void showTransactionView(ActionEvent actionEvent) {
		rootWindow.setCenter(transactionView.getView());
	}
	
	public void showPostView(ActionEvent actionEvent) {
		rootWindow.setCenter(postView.getView());
	}
}
