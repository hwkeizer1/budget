package nl.budget.view.post;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import nl.garvelink.iban.IBAN;

@Component
public class PostView extends AbstractView {
	
	private final PostService  postService;

	private VBox mainView;
	private TableView<Post> postTableView;
	
	DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");

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
		
		TableColumn<Post, LocalDate> monthYearColumn = new TableColumn<>(ViewConstant.POSTS_MONTH_YEAR);
		TableColumn<Post, String> categoryColumn = new TableColumn<>(ViewConstant.POSTS_CATEGORY);
		TableColumn<Post, BigDecimal> startBalanceColumn = new TableColumn<>(ViewConstant.POSTS_START_BALANCE);
		TableColumn<Post, BigDecimal> budgetColumn = new TableColumn<>(ViewConstant.POSTS_BUDGET);
		TableColumn<Post, BigDecimal> endBalanceColumn = new TableColumn<>(ViewConstant.POSTS_END_BALANCE);
		TableColumn<Post, String> accountColumn = new TableColumn<>(ViewConstant.POSTS_ACCOUNT);
		
		monthYearColumn.setCellValueFactory(cellData -> cellData.getValue().monthYearProperty());
		categoryColumn.setCellValueFactory(c -> c.getValue().categoryProperty());
		startBalanceColumn.setCellValueFactory(c -> c.getValue().startBalanceProperty());
		budgetColumn.setCellValueFactory(c -> c.getValue().budgetProperty());
		endBalanceColumn.setCellValueFactory(cellData -> cellData.getValue().endBalanceProperty());
		accountColumn.setCellValueFactory(cellData -> cellData.getValue().accountProperty().get().ibanProperty());

		/*
		 * Format month-year column to show year and month only
		 */
		monthYearColumn.setCellFactory(c -> new TableCell<Post, LocalDate>() {
			@Override
			protected void updateItem(LocalDate value, boolean empty) {
				super.updateItem(value, empty);
				if (!empty) {
					setText(value.format(yearMonthFormatter));
				}
			}
		});
		
		startBalanceColumn.setCellFactory(c -> new TableCell<Post, BigDecimal>() {
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
		
		endBalanceColumn.setCellFactory(c -> new TableCell<Post, BigDecimal>() {
			@Override
			protected void updateItem(BigDecimal value, boolean empty) {
				super.updateItem(value, empty);
				if (!empty) {
					Util.styleBigDecimalNode(value, this);
					setText(value.toString());
				}
			}
		});
		
		/*
		 * Format account column to pretty IBAN format
		 */
		accountColumn.setCellFactory(c -> new TableCell<Post, String>() {
			@Override
			protected void updateItem(String value, boolean empty) {
				super.updateItem(value, empty);
				if (!empty) {
					setText(IBAN.toPretty(value));
				}
			}
		});
		
		postTableView.getColumns().add(monthYearColumn);
		postTableView.getColumns().add(categoryColumn);
		postTableView.getColumns().add(startBalanceColumn);
		postTableView.getColumns().add(budgetColumn);
		postTableView.getColumns().add(endBalanceColumn);
		postTableView.getColumns().add(accountColumn);

		return postTableView;
	}

}
