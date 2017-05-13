package pl.com.bottega.exchangerate.api;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExchangeRateDto {

	private LocalDate date;
	private String currency;
	private BigDecimal rate;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}
