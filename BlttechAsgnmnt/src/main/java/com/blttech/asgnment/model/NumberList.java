/**
 * 
 */
package com.blttech.asgnment.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 
 * Number List data model
 * 
 * @author Somendu Maiti
 *
 * @Since 14-Mar-2021
 */

@Data
@JsonSerialize
public class NumberList implements Serializable {

	private String id;
	private List<Integer> nums;
	private int sum;
}
