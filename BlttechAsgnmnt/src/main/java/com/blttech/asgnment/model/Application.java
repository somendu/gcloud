/**
 * 
 */
package com.blttech.asgnment.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Main Class for Application
 * 
 * @author Somendu Maiti
 *
 * @Since 14-Mar-2021
 */
@Data
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonSerialize
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Application {

	private Applicant applicant;

	private String role;
	private String referrer;

	private Answer answer;
//	private MultipartFile file;
//	private MultipartFile source;

}
