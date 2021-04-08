/**
 * 
 */
package com.blttech.asgnment.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Somendu Maiti
 *
 * @Since 16-Mar-2021
 */
@Data
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Accessors(chain = true)

public class Applicant {
	private String firstName;
	private String lastName;
}