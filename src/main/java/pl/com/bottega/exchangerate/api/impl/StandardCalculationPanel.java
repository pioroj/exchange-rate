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
		String from = command.getFrom();
		String to = command.getTo();
		String date = command.getDate();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateFormat = LocalDate.parse(date, dtf);
		BigDecimal amount = command.getAmount();

		BigDecimal calculatedAmount = BigDecimal.ONE;

		if (from.equals("PLN")) {
			ExchangeRate exchangeRate = exchangeRatesRepository.get(dateFormat, to);
			BigDecimal rate = exchangeRate.getRate();
			calculatedAmount = amount.divide(rate, new MathContext(4));
		}

		if (to.equals("PLN")) {
			ExchangeRate exchangeRate = exchangeRatesRepository.get(dateFormat, from);
			BigDecimal rate = exchangeRate.getRate();
			calculatedAmount = amount.multiply(rate);
		}

		if (!(to.equals("PLN")) && !(from.equals("PLN"))) {
			ExchangeRate exchangeRateTo = exchangeRatesRepository.get(dateFormat, to);
			BigDecimal rateTo = exchangeRateTo.getRate();
			ExchangeRate exchangeRateFrom = exchangeRatesRepository.get(dateFormat, from);
			BigDecimal rateFrom = exchangeRateFrom.getRate();
			calculatedAmount = (amount.multiply(rateFrom)).divide(rateTo, new MathContext(4));
		}

		return new CalculationResult(from, to, date, amount, calculatedAmount);
	}


}
