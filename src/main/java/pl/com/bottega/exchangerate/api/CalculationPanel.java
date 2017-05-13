package pl.com.bottega.exchangerate.api;

import pl.com.bottega.exchangerate.domain.CalculationResult;
import pl.com.bottega.exchangerate.domain.commands.CalculationRequest;

public interface CalculationPanel {

	CalculationResult calculate(CalculationRequest command);

}
