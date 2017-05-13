package pl.com.bottega.exchangerate.api.impl;

import pl.com.bottega.exchangerate.api.CalculationPanel;
import pl.com.bottega.exchangerate.api.ExchangeRateDto;
import pl.com.bottega.exchangerate.api.RatesCatalog;
import pl.com.bottega.exchangerate.domain.CalculationResult;
import pl.com.bottega.exchangerate.domain.ExchangeRatesRepository;
import pl.com.bottega.exchangerate.domain.commands.CalculationRequestCommand;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Transactional
public class StandardCalculationPanel implements CalculationPanel {

	private ExchangeRatesRepository exchangeRatesRepository;
	private RatesCatalog ratesCatalog;

	public StandardCalculationPanel(ExchangeRatesRepository exchangeRatesRepository, RatesCatalog ratesCatalog) {
		this.exchangeRatesRepository = exchangeRatesRepository;
		this.ratesCatalog = ratesCatalog;
	}

	@Override
	public CalculationResult calculate(CalculationRequestCommand command) {
		String from = command.getFrom();
		String to = command.getTo();
		String date = command.getDate();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateFormat = LocalDate.parse(date, dtf);
		BigDecimal amount = command.getAmount();

		BigDecimal calculatedAmount = BigDecimal.ONE;

		if (from.equals("PLN")) {
			ExchangeRateDto exchangeRateDto = ratesCatalog.get(dateFormat, to);
			BigDecimal rate = exchangeRateDto.getRate();
			calculatedAmount = amount.divide(rate, new MathContext(4));
		}

		if (to.equals("PLN")) {
			ExchangeRateDto exchangeRateDto = ratesCatalog.get(dateFormat, from);
			BigDecimal rate = exchangeRateDto.getRate();
			calculatedAmount = amount.multiply(rate);
		}

		if (!(to.equals("PLN")) && !(from.equals("PLN"))) {
			ExchangeRateDto exchangeRateDtoTo = ratesCatalog.get(dateFormat, to);
			BigDecimal rateTo = exchangeRateDtoTo.getRate();
			ExchangeRateDto exchangeRateDtoFrom = ratesCatalog.get(dateFormat, from);
			BigDecimal rateFrom = exchangeRateDtoFrom.getRate();
			calculatedAmount = (amount.multiply(rateFrom)).divide(rateTo, new MathContext(4));
		}

		return new CalculationResult(from, to, date, amount, calculatedAmount);
	}


}
