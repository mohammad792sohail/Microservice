package com.microservices.currency_exchange_service;

import java.math.BigDecimal;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment environment;
	
	//Environment is a Spring-provided interface that allows access to application properties and environment variables.
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeVlaue(@PathVariable String from,@PathVariable String to) {
		CurrencyExchange currencyexchange=repository.findByFromAndTo(from, to);
		if(currencyexchange==null) {
			throw new RuntimeException("unable to find the data" + from + "to" +to);
		}
		String port=environment.getProperty("local.server.port");
		currencyexchange.setEnvironment(port);
		  return currencyexchange;
	}
	

}
