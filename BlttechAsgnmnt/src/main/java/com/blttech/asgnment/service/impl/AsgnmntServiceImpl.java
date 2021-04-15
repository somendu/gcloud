/**
 * 
 */
package com.blttech.asgnment.service.impl;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.blttech.asgnment.config.BlttechProperties;
import com.blttech.asgnment.model.Application;
import com.blttech.asgnment.model.NumberList;
import com.blttech.asgnment.service.AsgnmntService;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

/**
 * Assignment Service Implmentation
 * 
 * @author Somendu Maiti
 *
 * @Since 13-Mar-2021
 */
@Service
@RequiredArgsConstructor
public class AsgnmntServiceImpl implements AsgnmntService {

	private final RestTemplate restTemplate;

	private final BlttechProperties blttechProperties;

	@Override
	public NumberList getTotal() {

		String numList = restTemplate.getForObject(blttechProperties.getServer() + blttechProperties.getApplication(),
				String.class);

		JSONObject jsonObject = new JSONObject(numList);

		NumberList numberList = new Gson().fromJson(jsonObject.toString(), NumberList.class);

		// Logic to calculate the sum
		List<Integer> integerList = numberList.getNums();

		int total = integerList.stream().reduce(0, (a, b) -> a + b);
		numberList.setSum(total);

		return numberList;
	}

	@Override
	public String submitAnswer(Application application, MultipartFile file, MultipartFile source) throws IOException {

		String submitAnswer = "";

		LinkedMultiValueMap<String, Object> requestBodyMap = new LinkedMultiValueMap<String, Object>();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		// Creating first part
		requestBodyMap.add("application", application);

		// Creating second part
		headers.setContentDisposition(ContentDisposition.parse("form-data; name=" + file.getOriginalFilename()));

		requestBodyMap.add("file", new ByteArrayResource(file.getBytes()) {
			@Override
			public String getFilename() {
				return file.getOriginalFilename();
			}
		});

		// Creating third part part
		headers.setContentDisposition(ContentDisposition.parse("form-data; name=" + source.getOriginalFilename()));

		requestBodyMap.add("source", new ByteArrayResource(source.getBytes()) {
			@Override
			public String getFilename() {
				return source.getOriginalFilename();
			}
		});

		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<LinkedMultiValueMap<String, Object>>(
				requestBodyMap, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(
				blttechProperties.getServer() + blttechProperties.getApplication(), requestEntity, String.class);

		submitAnswer = response.getBody();

		return submitAnswer;
	}

}
