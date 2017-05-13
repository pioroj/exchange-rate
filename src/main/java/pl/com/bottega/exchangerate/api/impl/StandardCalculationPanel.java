package pl.com.bottega.exchangerate.api.impl;

import pl.com.bottega.exchangerate.api.CalculationPanel;
import pl.com.bottega.exchangerate.domain.CalculationResult;
import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRatesRepository;
import pl.com.bottega.exchangerate.domain.commands.CalculationRequest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Transactional
public class StandardCalculationPanel implements CalculationPanel {

	private static final DateTimeFormatter STANDARD_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private ExchangeRatesRepository exchangeRatesRepository;

	public StandardCalculationPanel(ExchangeRatesRepository exchangeRatesRepository) {
		this.exchangeRatesRepository = exchangeRatesRepository;
	}

	@Override
	public CalculationResult calculate(CalculationRequest command) {
		String currencyFrom = command.getFrom();
		String currencyTo = command.getTo();
		LocalDate date = LocalDate.parse(command.getDate(), STANDARD_DATE_TIME_FORMATTER);
		BigDecimal amount = command.getAmount();

		ExchangeRate exchangeRateFrom = getExchangeRate(date, currencyFrom);
		ExchangeRate exchangeRateTo = getExchangeRate(date, currencyTo);

		return CalculationResult.of(exchangeRateFrom, exchangeRateTo, amount, command.getDate());
	}

	private ExchangeRate getExchangeRate(LocalDate date, String currencyFrom) {
		ExchangeRate exchangeRate;
		if (currencyFrom.equals(ExchangeRate.getDefaultCurrency())) {
			exchangeRate = ExchangeRate.getDefault();
		} else {
			exchangeRate = exchangeRatesRepository.get(date, currencyFrom);
		}
		return exchangeRate;
	}


}
