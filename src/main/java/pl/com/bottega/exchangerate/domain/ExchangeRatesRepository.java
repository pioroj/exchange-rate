package pl.com.bottega.exchangerate.domain;

public interface ExchangeRatesRepository {

	void put(ExchangeRate exchangeRate);

	ExchangeRate get(Long id);

}
