/**
 * 
 */
package com.blttech.asgnment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * Main Class for Application
 * 
 * @author Somendu Maiti
 *
 * @Since 14-Mar-2021
 */
@Data
@JsonSerialize
public class Application {

	private Applicant applicant;

	private String role;

	private String referrer;

	@JsonProperty("answer")
	private Answer answer;

}
