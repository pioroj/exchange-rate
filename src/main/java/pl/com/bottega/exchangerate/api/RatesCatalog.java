package pl.com.bottega.exchangerate.api;

import pl.com.bottega.exchangerate.domain.ExchangeRate;

import java.time.LocalDate;

public interface RatesCatalog {

	ExchangeRateDto get(LocalDate date, String currency);

}
