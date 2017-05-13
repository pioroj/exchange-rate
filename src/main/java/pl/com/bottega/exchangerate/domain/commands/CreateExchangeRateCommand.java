package pl.com.bottega.exchangerate.domain.commands;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateExchangeRateCommand implements Validatable {

	private LocalDate date;
	private String currency;
	private BigDecimal rate;

	public CreateExchangeRateCommand() {
	}

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

	@Override
	public void validate(ValidationErrors errors) {
		validateDate(errors);
		validateCurrency(errors);
		validateRate(errors);
	}

	private void validateRate(ValidationErrors errors) {
		if(rate == null)
			errors.add("rate", "is required");
		if (rate != null && rate.compareTo(BigDecimal.ZERO) < 0)
			errors.add("rate", "must be > than 0.0");
	}

	private void validateDate(ValidationErrors errors) {
		if (date == null)
			errors.add("date", "is required");
	}

	private void validateCurrency(ValidationErrors errors) {
		if (isEmpty(currency))
			errors.add("currency", "is required");
		if (currency != null && currency.length() != 3)
			errors.add("currency", "has invalid format");
	}
}
