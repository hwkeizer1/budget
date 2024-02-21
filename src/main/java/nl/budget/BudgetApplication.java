package nl.budget;

import java.util.Locale;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;

@SpringBootApplication
public class BudgetApplication {

	public static void main(String[] args) {
		setDefaultSettings();
		Application.launch(BudgetApplicationFx.class, args);
	}

	private static void setDefaultSettings() {
		Locale nlLocal = new Locale.Builder().setLanguage("nl").setRegion("NL").build();
		Locale.setDefault(nlLocal);
	}
}
