package pl.com.bottega.exchangerate.infrastructure;

import pl.com.bottega.exchangerate.domain.ExchangeRate;
import pl.com.bottega.exchangerate.domain.ExchangeRatesRepository;
import pl.com.bottega.exchangerate.domain.NoRateException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class JPAExchangeRatesRepository implements ExchangeRatesRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void put(ExchangeRate exchangeRate) {
		entityManager.persist(exchangeRate);
	}

	@Override
	public ExchangeRate get(LocalDate date, String currency) {
		Query query = entityManager.createQuery("FROM ExchangeRate e WHERE e.date =:date AND e.currency =:currency)");
		query.setParameter("date", date);
		query.setParameter("currency", currency);
		List<ExchangeRate> exchangeRates = query.getResultList();
		if (exchangeRates.isEmpty())
			throw new NoRateException();
		ExchangeRate exchangeRate = (ExchangeRate) query.getResultList().get(0);
		return exchangeRate;
	}

	@Override
	public void removeExistingRates(LocalDate date, String currency) {
		Query query = entityManager.createQuery("FROM ExchangeRate e WHERE e.date =:date AND e.currency =:currency)");
		query.setParameter("date", date);
		query.setParameter("currency", currency);
		List<ExchangeRate> exchangeRates = query.getResultList();
		for (ExchangeRate exchangeRate : exchangeRates) {
			entityManager.remove(exchangeRate);
		}
	}

}
