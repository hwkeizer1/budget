package nl.budget.view.util.filter;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextFormatter;

public class CurrencyFilter implements UnaryOperator<TextFormatter.Change> {

	private static final String CURRENCY_REGEX_WITH_THOUSAND_SEPARATOR = "^-?(?:\\d{1,3}(?:\\.\\d{3})*(?:,\\d{0,2})?|\\d{1,3}(?:,\\d{0,2})?|\\d{1,3}(?:\\.\\d{0,2})?)$";
	private static final String CURRENCY_REGEX = "^[-+]?[0-9]*\\,?[0-9]?[0-9]?+$";

	@Override
	public TextFormatter.Change apply(TextFormatter.Change change) {

		String regex;
		if (change.getControlNewText().contains(".")) {
			regex = CURRENCY_REGEX_WITH_THOUSAND_SEPARATOR;
		} else {
			regex = CURRENCY_REGEX;
		}

		if (change.getControlNewText().matches(regex)) {
			return change;
		} else if ("-".equals(change.getText())) {
			if (change.getControlNewText().startsWith("-")) {
				change.setText(""); // remove instead of adding
				change.setRange(0, 1);
			} else {
				change.setRange(0, 0);
			}
			return change;
		}
		return null;
	}
}
