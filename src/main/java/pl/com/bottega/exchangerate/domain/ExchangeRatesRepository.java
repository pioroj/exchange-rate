package pl.com.bottega.exchangerate.domain;

import java.time.LocalDate;

public interface ExchangeRatesRepository {

	void put(ExchangeRate exchangeRate);

	ExchangeRate get(LocalDate date, String currency);

	void removeExistingRates(LocalDate date, String currency);

}
