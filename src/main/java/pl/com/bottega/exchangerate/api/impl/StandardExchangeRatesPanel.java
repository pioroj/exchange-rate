package pl.com.bottega.exchangerate.api.impl;

import pl.com.bottega.exchangerate.api.ExchangeRatesPanel;
import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRatesRepository;
import pl.com.bottega.exchangerate.domain.commands.CreateExchangeRateCommand;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Transactional
public class StandardExchangeRatesPanel implements ExchangeRatesPanel {

	private ExchangeRatesRepository exchangeRatesRepository;

	public StandardExchangeRatesPanel(ExchangeRatesRepository exchangeRatesRepository) {
		this.exchangeRatesRepository = exchangeRatesRepository;
	}

	@Override
	public void createExchangeRates(CreateExchangeRateCommand command) {
		LocalDate date = command.getDate();
		String currency = command.getCurrency();
		BigDecimal rate = command.getRate();
		ExchangeRate exchangeRate = new ExchangeRate(date, currency, rate);
		exchangeRatesRepository.removeExistingRates(date, currency);
		exchangeRatesRepository.put(exchangeRate);
	}

}
