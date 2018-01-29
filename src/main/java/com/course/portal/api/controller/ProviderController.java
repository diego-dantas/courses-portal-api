package com.course.portal.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dto.ProviderDTO;


@RestController
@RequestMapping(value = "/api")
public class ProviderController {
	
	@PostMapping(value = "/login")
	public ResponseEntity<Response<ProviderDTO>> loginProvider(@RequestBody ProviderDTO providerDTO){
		Response<ProviderDTO> response = new Response<ProviderDTO>();
		
		if(providerDTO.getEmail().equals("admin") && providerDTO.getPassword().equals("admin")) {
			providerDTO.set_id(1L);
			response.setData(providerDTO);
			return ResponseEntity.ok(response);
		}else {
			response.setData(providerDTO);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

}
