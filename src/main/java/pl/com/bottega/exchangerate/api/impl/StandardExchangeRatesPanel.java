package pl.com.bottega.exchangerate.api.impl;

import pl.com.bottega.exchangerate.api.ExchangeRatesPanel;
import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRatesRepository;
import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;

import javax.transaction.Transactional;

@Transactional
public class StandardExchangeRatesPanel implements ExchangeRatesPanel {

	private ExchangeRatesRepository exchangeRatesRepository;

	public StandardExchangeRatesPanel(ExchangeRatesRepository exchangeRatesRepository) {
		this.exchangeRatesRepository = exchangeRatesRepository;
	}

	@Override
	public void createExchangeRates(CreateExchangeRateCommand command) {
		ExchangeRate exchangeRate = new ExchangeRate(command.getDate(), command.getCurrency(), command.getRate());
		exchangeRatesRepository.put(exchangeRate);
	}

}
