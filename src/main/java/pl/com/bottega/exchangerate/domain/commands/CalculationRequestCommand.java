package pl.com.bottega.exchangerate.domain.commands;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

public class CalculationRequestCommand implements Validatable {

	private String from;
	private String to;
	private String date;
	private BigDecimal amount;

	public CalculationRequestCommand() {
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

	@Override
	public void validate(ValidationErrors errors) {
		validateFrom(errors);
		validateTo(errors);
		validateDate(errors);
		validateAmount(errors);
		validateSameCurrencies(errors);
	}

	private void validateSameCurrencies(ValidationErrors errors) {
		if (from != null && to != null && date != null && amount != null && from.equals(to)) {
			errors.add("to", "must be different than from");
			errors.add("from", "must be different than to");
		}
	}

	private void validateAmount(ValidationErrors errors) {
		if (amount == null)
			errors.add("amount", "is required");
	}

	private void validateDate(ValidationErrors errors) {
		if (date == null)
			errors.add("date", "is required");
	}

	private void validateTo(ValidationErrors errors) {
		if (isEmpty(to))
			errors.add("to", "is required");
	}

	private void validateFrom(ValidationErrors errors) {
		if (isEmpty(from))
			errors.add("from", "is required");
	}
}
