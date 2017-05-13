package pl.com.bottega.exchangerate.api.impl;

import pl.com.bottega.exchangerate.api.CalculationPanel;
import pl.com.bottega.exchangerate.domain.CalculationResult;
import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRatesRepository;
import pl.com.bottega.exchangerate.domain.commands.CalculationRequest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.MathContext;
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

		ExchangeRate exchangeRateFrom = getExchangeRate(date, currencyFrom);
		ExchangeRate exchangeRateTo = getExchangeRate(date, currencyTo);

		BigDecimal calculatedAmount = BigDecimal.ONE;

		BigDecimal rateTo = exchangeRateTo.getRate();
		BigDecimal rateFrom = exchangeRateFrom.getRate();

		if (currencyFrom.equals(ExchangeRate.getDefaultCurrency())) {
			calculatedAmount = amount.divide(rateTo, new MathContext(4));
		}

		if (currencyTo.equals(ExchangeRate.getDefaultCurrency())) {
			calculatedAmount = amount.multiply(rateFrom);
		}

		if (!(currencyTo.equals(ExchangeRate.getDefaultCurrency())) && !(currencyFrom.equals(ExchangeRate.getDefaultCurrency()))) {
			calculatedAmount = (amount.multiply(rateFrom)).divide(rateTo, new MathContext(4));
		}

		return new CalculationResult(currencyFrom, currencyTo, command.getDate(), amount, calculatedAmount);
	}

	private ExchangeRate getExchangeRate(LocalDate date, String currencyFrom1) {
		ExchangeRate exchangeRateFrom;
		if (currencyFrom1.equals(ExchangeRate.getDefaultCurrency())) {
			exchangeRateFrom = ExchangeRate.getDefault();
		} else {
			exchangeRateFrom = exchangeRatesRepository.get(date, currencyFrom1);
		}
		return exchangeRateFrom;
	}


}
