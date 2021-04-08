/**
 * 
 */
package com.blttech.asgnment.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import lombok.Data;

/**
 * @author Somendu Maiti
 *
 * @Since 16-Mar-2021
 */
@Data
@JsonAutoDetect(fieldVisibility = Visibility.ANY)

public class Answer {
	private String questionId;
	private int sum;
}