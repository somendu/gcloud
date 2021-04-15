/**
 * 
 */
package com.blttech.asgnment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * @author Somendu Maiti
 *
 * @Since 16-Mar-2021
 */
@Data
@JsonSerialize
public class Answer {

	@JsonProperty("questionId")
	private String questionId;

	private int sum;
}