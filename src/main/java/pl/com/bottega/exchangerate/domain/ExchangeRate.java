package pl.com.bottega.exchangerate.domain;

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

	private LocalDate date;
	private String currency;
	private BigDecimal rate;

	public ExchangeRate(LocalDate date, String currency, BigDecimal rate) {
		this.date = date;
		this.currency = currency;
		this.rate = rate;
	}
}
