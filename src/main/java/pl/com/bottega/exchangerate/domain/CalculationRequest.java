package pl.com.bottega.exchangerate.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CalculationRequest {

	private String from;
	private String to;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private BigDecimal amount;

	public CalculationRequest() {
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public LocalDate getDate() {
		return date;
	}

	public BigDecimal getAmount() {
		return amount;
	}
}
