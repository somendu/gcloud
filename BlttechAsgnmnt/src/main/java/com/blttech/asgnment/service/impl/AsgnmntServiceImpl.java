/**
 * 
 */
package com.blttech.asgnment.service.impl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.blttech.asgnment.config.BlttechProperties;
import com.blttech.asgnment.model.NumberList;
import com.blttech.asgnment.service.AsgnmntService;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

/**
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
	public int getTotal() {

		var numList = restTemplate.getForObject(blttechProperties.getServer() + blttechProperties.getApplication(),
				String.class);

		JSONObject jsonObject = new JSONObject(numList);

		NumberList numberList = new Gson().fromJson(jsonObject.toString(), NumberList.class);

		String id = numberList.getId();
		List<Integer> integerList = numberList.getNums();

		int total = integerList.stream().reduce(0, (a, b) -> a + b);

		System.out.println("" + jsonObject);
		System.out.println("Total : " + total);

		return total;
	}

}
