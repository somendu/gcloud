package com.blttech.asgnment.controller;

import java.io.IOException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blttech.asgnment.model.Application;
import com.blttech.asgnment.model.NumberList;
import com.blttech.asgnment.service.AsgnmntService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * Controller
 * 
 * @author Somendu Maiti
 *
 * @Since 14-Mar-2021
 */
@RestController
@RequestMapping("/api")
public class MainController {

	@Autowired
	private AsgnmntService asgnmntService;

	/**
	 * Getting sum
	 * 
	 * @return JSON Object with total
	 * @throws IOException
	 */
	@RequestMapping(value = "/nums", method = RequestMethod.GET)
	public NumberList getTotal() {
		return asgnmntService.getTotal();
	}

	/**
	 * Submitting Application
	 * 
	 * @return String representing the status code
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws IOException
	 */

	@RequestMapping(value = "/application", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ResponseEntity<?> submitTotal(@RequestPart("application") @Valid Application application,
			@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file,
			@RequestPart("source") @Valid @NotNull @NotBlank MultipartFile source)
			throws JsonParseException, JsonMappingException, IOException {

		String submission = "";

		try {

			submission = asgnmntService.submitAnswer(application, file, source);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>(submission, HttpStatus.OK);

	}

}
