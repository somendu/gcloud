/**
 * 
 */
package com.blttech.asgnment.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blttech.asgnment.model.NumberList;

/**
 * Service Interface
 * 
 * @author Somendu Maiti
 *
 * @Since 13-Mar-2021
 */
@Service
public interface AsgnmntService {

	/**
	 * Getting the total
	 * 
	 * @return
	 */
	public NumberList getTotal();

	/**
	 * Submitting Answer
	 * 
	 * @return
	 */
	public String submitAnswer(String application, MultipartFile file, MultipartFile source) throws IOException;

	/**
	 * Submitting file
	 * 
	 * @return
	 */
	public String submitFile(MultipartFile file) throws IOException;

}
