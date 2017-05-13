package pl.com.bottega.exchangerate.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.exchangerate.api.CalculationPanel;
import pl.com.bottega.exchangerate.domain.CalculationResult;
import pl.com.bottega.exchangerate.domain.commands.CalculationRequest;

@RestController
public class CalculationController {

	private CalculationPanel calculationPanel;

	public CalculationController(CalculationPanel calculationPanel) {
		this.calculationPanel = calculationPanel;
	}

	@GetMapping("/calculation")
	CalculationResult calculate(CalculationRequest command) {
		return calculationPanel.calculate(command);
	}

}
