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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd")
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

	public String getCurrency() {
		return currency;
	}

	public BigDecimal getRate() {
		return rate;
	}
}
