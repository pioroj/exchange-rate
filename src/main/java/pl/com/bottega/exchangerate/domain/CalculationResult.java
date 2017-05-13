package pl.com.bottega.exchangerate.domain;

import java.math.BigDecimal;

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

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getCalculatedAmount() {
		return calculatedAmount;
	}

	public void setCalculatedAmount(BigDecimal calculatedAmount) {
		this.calculatedAmount = calculatedAmount;
	}
}
