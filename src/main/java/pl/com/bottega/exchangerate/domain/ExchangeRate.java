package pl.com.bottega.exchangerate.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ExchangeRate {

	private static final ExchangeRate DEFAULT = new ExchangeRate(null, "PLN", BigDecimal.ONE);
	private static final String DEFAULT_CURRENCY = "PLN";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate date;
	private String currency;
	private BigDecimal rate;

	public ExchangeRate(LocalDate date, String currency, BigDecimal rate) {
		this.date = date;
		this.currency = currency;
		this.rate = rate;
	}

	public ExchangeRate() {
	}

	public LocalDate getDate() {
		return date;
	}

	String getCurrency() {
		return currency;
	}

	BigDecimal getRate() {
		return rate;
	}

	public static ExchangeRate getDefault() {
		return DEFAULT;
	}

	public static String getDefaultCurrency() {
		return DEFAULT_CURRENCY;
	}
}
