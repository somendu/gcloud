/**
 * 
 */
package com.blttech.asgnment.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * @author Somendu Maiti
 *
 * @Since 16-Mar-2021
 */
@Data
@JsonSerialize
public class Applicant {

	private String firstName;

	private String lastName;
}