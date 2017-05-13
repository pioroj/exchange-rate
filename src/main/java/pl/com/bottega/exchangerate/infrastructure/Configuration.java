package pl.com.bottega.exchangerate.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.com.bottega.exchangerate.api.ExchangeRatesPanel;
import pl.com.bottega.exchangerate.api.impl.StandardExchangeRatesPanel;
import pl.com.bottega.exchangerate.domain.ExchangeRatesRepository;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	public ExchangeRatesRepository exchangeRatesRepository() {
		return new JPAExchangeRatesRepository();
	}

	@Bean
	public ExchangeRatesPanel exchangeRatesPanel(ExchangeRatesRepository exchangeRatesRepository) {
		return new StandardExchangeRatesPanel(exchangeRatesRepository);
	}

}
