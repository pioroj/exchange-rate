package pl.com.bottega.exchangerate.infrastructure;

import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRatesRepository;
import pl.com.bottega.exchangerate.domain.NoRateException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAExchangeRatesRepository implements ExchangeRatesRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void put(ExchangeRate exchangeRate) {
		entityManager.persist(exchangeRate);
	}

	@Override
	public ExchangeRate get(Long id) {
		ExchangeRate exchangeRate = entityManager.find(ExchangeRate.class, id);
		if (exchangeRate == null)
			throw new NoRateException();
		return exchangeRate;
	}

}
