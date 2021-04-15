/**
 * 
 */
package com.blttech.asgnment.service.impl;

import java.io.File;
import java.io.FileOutputStream;
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
import com.blttech.asgnment.model.ApplicantResponse;
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

		System.out.println("NumberList :" + numberList);
		return numberList;
	}

	@Override
	public String submitAnswer(String application, MultipartFile file, MultipartFile source) throws IOException {

		System.out.println("came here submitAnswer service impl");

		System.out.println(application);

		String submitAnswer = "";

		LinkedMultiValueMap<String, Object> requestBodyMap = new LinkedMultiValueMap<String, Object>();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		// Creating first part

		String jsonString = new Gson().toJson(application);
		requestBodyMap.add("application", jsonString);

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
//		LinkedMultiValueMap<String, Object> sourceBody = new LinkedMultiValueMap<String, Object>();
		requestBodyMap.add("source", new ByteArrayResource(source.getBytes()) {
			@Override
			public String getFilename() {
				return source.getOriginalFilename();
			}
		});

		System.out.println(blttechProperties.getServer() + blttechProperties.getApplication());

		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<LinkedMultiValueMap<String, Object>>(
				requestBodyMap, headers);

		ResponseEntity<?> response = restTemplate.postForEntity(
				blttechProperties.getServer() + blttechProperties.getApplication(), requestEntity,
				ApplicantResponse.class);
//
//		System.out.println("Headers : " + response.getResponse().getHeaders());
//		int statusCode = response.getResponse().getStatusCode().value();
		System.out.println("Headers : " + response.getHeaders());
		int statusCode = response.getStatusCodeValue();
////
		submitAnswer = (statusCode == 200) ? "Answer Submitted Successfully"
				: "Answer Not Submitted Successfully with status code error as " + statusCode;

		return submitAnswer;
	}

	@Override
	public String submitFile(MultipartFile file) throws IOException {

		String fileUploadReturnString = "";

		if (!file.isEmpty()) {

//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//			LinkedMultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<String, Object>();
//			bodyMap.add("file", new FileSystemResource(convert(file)));
//			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
//
//			ResponseEntity<ApplicantResponse> response = restTemplate.exchange(
//					blttechProperties.getServer() + blttechProperties.getApplication(), HttpMethod.POST, requestEntity,
//					ApplicantResponse.class);
//
//			int statusCode = response.getBody().getResponse().getStatusCode().value();
//
//			fileUploadReturnString = (statusCode == 200) ? "File Submitted Successfully"
//					: "File Not Submitted Successfully with status code error as " + statusCode;

//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//			headers.setContentDisposition(ContentDisposition.parse("form-data; name=myJsonString");
//			LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
//			body.add("file", new ByteArrayResource(file.getBytes()) {
//				@Override
//				public String getFilename() {
//					return file.getOriginalFilename();
//				}
//			});
//
//			ApplicantResponse response = restTemplate.postForObject(
//					blttechProperties.getServer() + blttechProperties.getApplication(), new HttpEntity<>(body, headers),
//					ApplicantResponse.class);
//
//			int statusCode = response.getResponse().getStatusCode().value();
//
//			fileUploadReturnString = (statusCode == 200) ? "File Submitted Successfully"
//					: "File Not Submitted Successfully with status code error as " + statusCode;

		}

		return fileUploadReturnString;
	}

	private static File convert(MultipartFile file) {
		File convFile = new File(file.getOriginalFilename());
		try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convFile;
	}
}
