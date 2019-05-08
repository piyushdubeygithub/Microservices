package com.promv.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.promv.filter.SimpleFilter;

@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class ProSmvApiGatewayApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProSmvApiGatewayApplication.class);

	public static void main(String[] args) {

		LOGGER.info("Pro SMV Api Gateway is started");

		SpringApplication.run(ProSmvApiGatewayApplication.class, args);

	}

	@Bean
	public SimpleFilter simpleFilter() {

		return new SimpleFilter();
	
	}

}
