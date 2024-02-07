package nl.budget.view.post;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nl.budget.model.Post;
import nl.budget.service.PostService;
import nl.budget.view.AbstractView;
import nl.budget.view.ViewConstant;
import nl.budget.view.util.Util;

@Component
public class PostView extends AbstractView {
	
	private final PostService  postService;

	private VBox mainView;
	private TableView<Post> postTableView;

	public PostView(PostService postService) {
		this.postService = postService;
		
		initComponents();
	}

	@Override
	public void initComponents() {
		mainView = new VBox();
		mainView.getChildren().addAll(createPostTableView());
	}
	
	@Override
	public Pane getView() {
		return mainView;
	}

	private TableView<Post> createPostTableView() {
		
		postTableView = new TableView<>();
		postTableView.setPrefHeight(2000); // TODO: For now oversize so all rows are visible, solve with properties later
		postTableView.setItems(postService.getObservableList());
		
		TableColumn<Post, String> categoryColumn = new TableColumn<>(ViewConstant.POSTS_CATEGORY);
		TableColumn<Post, BigDecimal> reserveColumn = new TableColumn<>(ViewConstant.POSTS_RESERVE);
		TableColumn<Post, BigDecimal> budgetColumn = new TableColumn<>(ViewConstant.POSTS_BUDGET);
		TableColumn<Post, BigDecimal> balanceColumn = new TableColumn<>(ViewConstant.POSTS_BALANCE);
		TableColumn<Post, String> accountColumn = new TableColumn<>(ViewConstant.POSTS_ACCOUNT);
		
		categoryColumn.setCellValueFactory(c -> c.getValue().categoryProperty());
		reserveColumn.setCellValueFactory(c -> c.getValue().reserveProperty());
		budgetColumn.setCellValueFactory(c -> c.getValue().budgetProperty());
		balanceColumn.setCellValueFactory(cellData -> cellData.getValue().balanceProperty());
		accountColumn.setCellValueFactory(cellData -> cellData.getValue().accountProperty().get().ibanProperty());

		reserveColumn.setCellFactory(c -> new TableCell<Post, BigDecimal>() {
			@Override
			protected void updateItem(BigDecimal value, boolean empty) {
				super.updateItem(value, empty);
				if (!empty) {
					Util.styleBigDecimalNode(value, this);
					setText(value.toString());
				}
			}
		});
		
		budgetColumn.setCellFactory(c -> new TableCell<Post, BigDecimal>() {
			@Override
			protected void updateItem(BigDecimal value, boolean empty) {
				super.updateItem(value, empty);
				if (!empty) {
					Util.styleBigDecimalNode(value, this);
					setText(value.toString());
				}
			}
		});
		
		balanceColumn.setCellFactory(c -> new TableCell<Post, BigDecimal>() {
			@Override
			protected void updateItem(BigDecimal value, boolean empty) {
				super.updateItem(value, empty);
				if (!empty) {
					Util.styleBigDecimalNode(value, this);
					setText(value.toString());
				}
			}
		});
		
		postTableView.getColumns().add(categoryColumn);
		postTableView.getColumns().add(reserveColumn);
		postTableView.getColumns().add(budgetColumn);
		postTableView.getColumns().add(balanceColumn);
		postTableView.getColumns().add(accountColumn);

		return postTableView;
	}

}
