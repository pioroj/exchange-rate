package pl.com.bottega.exchangerate.api;

import pl.com.bottega.exchangerate.domain.CalculationResult;
import pl.com.bottega.exchangerate.domain.commands.CalculationRequestCommand;

public interface CalculationPanel {

	CalculationResult calculate(CalculationRequestCommand command);

}
