package com.somendu;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Entry Point Class
 * 
 * @author Somendu Maiti
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCaching
public class Main extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return configureApplication(builder);
	}

	public static void main(String[] args) {

		SpringApplication.run(Main.class, args);
	}

	private static SpringApplicationBuilder configureApplication(
			SpringApplicationBuilder builder) {
		return builder.sources(Main.class).bannerMode(Banner.Mode.OFF);
	}
}
