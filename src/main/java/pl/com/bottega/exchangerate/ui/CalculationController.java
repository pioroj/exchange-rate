package pl.com.bottega.exchangerate.ui;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.exchangerate.api.CalculationPanel;
import pl.com.bottega.exchangerate.domain.CalculationResult;
import pl.com.bottega.exchangerate.domain.commands.CalculationRequestCommand;

@RestController
public class CalculationController {

	private CalculationPanel calculationPanel;

	public CalculationController(CalculationPanel calculationPanel) {
		this.calculationPanel = calculationPanel;
	}

	@GetMapping("/calculation")
	CalculationResult calculate(CalculationRequestCommand command) {
		return calculationPanel.calculate(command);
	}

}
