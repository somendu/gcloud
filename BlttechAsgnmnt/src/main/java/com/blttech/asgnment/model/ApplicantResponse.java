/**
 * 
 */
package com.blttech.asgnment.model;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * Response Class
 * 
 * @author Somendu Maiti
 *
 * @Since 14-Mar-2021
 */
@Data
@JsonSerialize
public class ApplicantResponse implements Serializable {
	private ResponseEntity<?> response;
}
