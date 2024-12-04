package org.com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class MainController {
	@GetMapping(path = "/test/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getStatus() {
		Map<String, Object> mapres = new HashMap<>();
		mapres.put("status", "OK!");
		mapres.put("mensaje", "!!! Los servicios están activos. ¡¡¡");

		String res = new Gson().toJson(mapres);

		log.info("HEALTHCHECK {} ",  res);
	

		return res;

	}
	
	

	@PostConstruct
	public void status() {
		getStatus();
	}

}
