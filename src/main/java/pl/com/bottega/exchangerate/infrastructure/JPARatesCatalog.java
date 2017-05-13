package pl.com.bottega.exchangerate.infrastructure;

import pl.com.bottega.exchangerate.api.ExchangeRateDto;
import pl.com.bottega.exchangerate.api.RatesCatalog;
import pl.com.bottega.exchangerate.domain.ExchangeRate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;

public class JPARatesCatalog implements RatesCatalog {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ExchangeRateDto get(LocalDate date, String currency) {
		Query query = entityManager.createQuery("FROM ExchangeRate e WHERE e.date =:date AND e.currency =:currency)");
		query.setParameter("date", date);
		query.setParameter("currency", currency);
		ExchangeRate exchangeRate = (ExchangeRate) query.getResultList().get(0);
		ExchangeRateDto exchangeRateDto = createRateDto(exchangeRate);
		return exchangeRateDto;
	}

	private ExchangeRateDto createRateDto(ExchangeRate exchangeRate) {
		ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
		exchangeRateDto.setCurrency(exchangeRate.getCurrency());
		exchangeRateDto.setDate(exchangeRate.getDate());
		exchangeRateDto.setRate(exchangeRate.getRate());
		return exchangeRateDto;
	}
}
