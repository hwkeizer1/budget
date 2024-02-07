package nl.budget.view.util;

import java.math.BigDecimal;

import javafx.scene.Node;

public class Util {
	
	private Util() {}

	public static Node styleBigDecimalNode(BigDecimal value, Node node) {
		if (value.compareTo(BigDecimal.ZERO) >= 0) {
			node.setStyle("-fx-text-fill: green;");
		} else {
			node.setStyle("-fx-text-fill: red;");
		}
		return node;
	}
}
