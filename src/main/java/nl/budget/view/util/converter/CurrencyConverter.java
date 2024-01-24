package nl.budget.view.util.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javafx.util.converter.BigDecimalStringConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CurrencyConverter extends BigDecimalStringConverter {

	DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());

	@Override
	public String toString(BigDecimal value) {
		if (value == null) {
			return "";
		}
		String valueString = df.format(value);
		// TODO HK Strip out thousand separators for now because they are not part of the current regex, needs fix
		String test = valueString.replace(".", "");
		return test;
	}

	@Override
	public BigDecimal fromString(String valueString) {
		if (valueString != null && !valueString.isEmpty()) {
			try {
				df.setParseBigDecimal(true);
				return ((BigDecimal) df.parseObject(valueString)).setScale(2, RoundingMode.HALF_UP);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
