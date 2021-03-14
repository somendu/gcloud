package com.blttech.asgnment.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blttech.asgnment.service.AsgnmntService;

/**
 * Controller
 * 
 * @author Somendu
 *
 * @since 21-Jul-2020
 */
@RestController
@RequestMapping("/api")
public class MainController {

	@Autowired
	private AsgnmntService asgnmntService;

	/**
	 * Simple REST method without params
	 * 
	 * @return String representing the greeting
	 * @throws IOException
	 */
	@RequestMapping(value = "/nums", method = RequestMethod.GET)
	public int getTotal() {
		return asgnmntService.getTotal();
	}

	/**
	 * Simple REST method without params
	 * 
	 * @return String representing the greeting
	 * @throws IOException
	 */
	@RequestMapping(value = "/total", method = RequestMethod.POST)
	public void submitTotal() {

	}

	/**
	 * Simple REST method without params
	 * 
	 * @return String representing the greeting
	 * @throws IOException
	 */
	@RequestMapping(value = "/resume", method = RequestMethod.POST)
	public void submitResume() {

	}

	/**
	 * Simple REST method without params
	 * 
	 * @return String representing the greeting
	 * @throws IOException
	 */
	@RequestMapping(value = "/code", method = RequestMethod.POST)
	public void submitCode() {

	}
}
