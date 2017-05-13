package pl.com.bottega.exchangerate.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CalculationResult {

	private String from;
	private String to;
	private String date;
	private BigDecimal amount;
	private BigDecimal calculatedAmount;

	public CalculationResult(String from, String to, String date, BigDecimal amount, BigDecimal calculatedAmount) {
		this.from = from;
		this.to = to;
		this.date = date;
		this.amount = amount;
		this.calculatedAmount = calculatedAmount;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getDate() {
		return date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getCalculatedAmount() {
		return calculatedAmount;
	}

	public static CalculationResult of(ExchangeRate exchangeRateFrom,
									   ExchangeRate exchangeRateTo,
									   BigDecimal amount,
									   String date) {
		BigDecimal rateFrom = exchangeRateFrom.getRate();
		BigDecimal rateTo = exchangeRateTo.getRate();
		BigDecimal calculatedAmount = (amount.multiply(rateFrom).divide(rateTo, 2, BigDecimal.ROUND_FLOOR));

		String currencyFrom = exchangeRateFrom.getCurrency();
		String currencyTo = exchangeRateTo.getCurrency();
		return new CalculationResult(currencyFrom, currencyTo, date, amount, calculatedAmount);

	}
}
