package nl.budget.controllers;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxmlView;

@Slf4j
@Controller
@FxmlView("root.fxml")
public class RootController {

	@FXML
	Label testLabel;
}
