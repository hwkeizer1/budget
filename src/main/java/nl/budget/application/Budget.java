package nl.budget.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;

@SpringBootApplication(scanBasePackages = "nl.budget")
public class Budget {

	public static void main(String[] args) {
		Application.launch(BudgetFX.class, args);
	}

}
