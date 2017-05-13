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

	private ExchangeRatesRepository exchangeRatesRepository;

	public StandardCalculationPanel(ExchangeRatesRepository exchangeRatesRepository) {
		this.exchangeRatesRepository = exchangeRatesRepository;
	}

	@Override
	public CalculationResult calculate(CalculationRequest command) {
		String currencyFrom = command.getFrom();
		String currencyTo = command.getTo();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(command.getDate(), dtf);
		BigDecimal amount = command.getAmount();

		BigDecimal rateFrom = getExchangeRate(date, currencyFrom).getRate();
		BigDecimal rateTo = getExchangeRate(date, currencyTo).getRate();
		BigDecimal calculatedAmount = (amount.multiply(rateFrom).divide(rateTo, 2, BigDecimal.ROUND_FLOOR));

		return new CalculationResult(currencyFrom, currencyTo, command.getDate(), amount, calculatedAmount);
	}

	private ExchangeRate getExchangeRate(LocalDate date, String currencyFrom) {
		ExchangeRate exchangeRateFrom;
		if (currencyFrom.equals(ExchangeRate.getDefaultCurrency())) {
			exchangeRateFrom = ExchangeRate.getDefault();
		} else {
			exchangeRateFrom = exchangeRatesRepository.get(date, currencyFrom);
		}
		return exchangeRateFrom;
	}


}
