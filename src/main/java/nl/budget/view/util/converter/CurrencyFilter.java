package nl.budget.view.util.converter;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextFormatter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CurrencyFilter implements UnaryOperator<TextFormatter.Change> {
	
	@Override
	public TextFormatter.Change apply(TextFormatter.Change change) {

		if (change.getControlNewText().matches("^[-+]?[0-9]*\\,?[0-9]?[0-9]?+$")) {
			return change;
		} else if ("-".equals(change.getText())) {
			if (change.getControlNewText().startsWith("-")) {
				change.setText(""); // remove instead of adding
				change.setRange(0,  1);
			} else {
				change.setRange(0, 0);
			}
			return change;
		}
		
		return null;
	}
}
