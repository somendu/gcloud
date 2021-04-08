package com.blttech.asgnment.controller;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	@RequestMapping(value = "/application", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> submitTotal(@RequestPart("application") String application,
			@RequestPart("file") MultipartFile file, @RequestPart("source") MultipartFile source)
			throws JsonParseException, JsonMappingException, IOException {

		JSONObject jsonObject = new JSONObject(application);

		try {

			String submission = asgnmntService.submitAnswer(application, file, source);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);

	}

}
