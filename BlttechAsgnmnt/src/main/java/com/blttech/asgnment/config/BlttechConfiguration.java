package com.blttech.asgnment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuring required beans if any
 * 
 * @author Somendu Maiti
 *
 * @Since 14-Mar-2021
 *
 */
@Configuration
public class BlttechConfiguration {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
