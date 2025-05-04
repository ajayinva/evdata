package com.ajay.evdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.evdata.exception.MessageDto;
import com.ajay.evdata.service.EvActionVO;
import com.ajay.evdata.service.EvDataService;

@RestController
@RequestMapping(value = "/api/v1/evdata/actions/updatemsrp")
public class EvDataActionController {

	@Autowired
	private EvDataService evDataService;
	
	@PostMapping
	public ResponseEntity<MessageDto> updateMsrp(
		@RequestBody 
		EvActionVO dto
	) {
		evDataService.update(dto);
		MessageDto message = MessageDto.builder()
				.code(HttpStatus.OK.value()+"")
				.message("Successfully updated")
				.build();
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
