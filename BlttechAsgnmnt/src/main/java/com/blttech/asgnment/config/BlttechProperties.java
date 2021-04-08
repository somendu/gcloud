/**
 * 
 */
package com.blttech.asgnment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Config for end points
 * 
 * @author Somendu Maiti
 *
 * @Since 14-Mar-2021
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "blttech.api")
public class BlttechProperties {

	private String server;
	private String application;

}
