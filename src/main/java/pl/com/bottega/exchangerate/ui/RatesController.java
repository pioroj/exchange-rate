package pl.com.bottega.exchangerate.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.exchangerate.api.ExchangeRatesPanel;
import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;

@RestController
public class RatesController {

	private ExchangeRatesPanel exchangeRatesPanel;

	public RatesController(ExchangeRatesPanel exchangeRatesPanel) {
		this.exchangeRatesPanel = exchangeRatesPanel;
	}

	@PutMapping("/exchange-rate")
	void create(@RequestBody CreateExchangeRateCommand command) {
		exchangeRatesPanel.createExchangeRates(command);
	}
}
