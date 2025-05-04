package com.ajay.evdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.evdata.exception.BadDataException;
import com.ajay.evdata.exception.MessageDto;
import com.ajay.evdata.exception.NoDataFoundException;
import com.ajay.evdata.service.EvDataService;
import com.ajay.evdata.service.EvDataVO;

@RestController
@RequestMapping(value = "/api/v1/evdata")
public class EvDataController {

	@Autowired
	private EvDataService evDataService;
	
	@GetMapping
	public ResponseEntity<List<EvDataVO>> getAllEvData() {
		//Implement Pagination and Return all data. Right now its hard coded to return 10 records
		//Add page number and offset
		return new ResponseEntity<>(evDataService.getAllEvData(), HttpStatus.OK);
	}
	

	@GetMapping("{vin}")
	public ResponseEntity<EvDataVO> getEvData(@PathVariable String vin) {
		EvDataVO vo = evDataService.getEvData(vin);
		if (vo != null) {
			return new ResponseEntity<>(vo, HttpStatus.OK);
		}
		throw new NoDataFoundException("No Data Found");
	}
	
	@PutMapping
	public ResponseEntity<EvDataVO> updateEvData(
		@RequestBody 
		EvDataVO dto
	) {
		EvDataVO vo = evDataService.updateEvData(dto);
		if (vo != null) {
			return new ResponseEntity<>(vo, HttpStatus.OK);
		}
		throw new NoDataFoundException("No Data Found");
	}
	
	@PostMapping
	public ResponseEntity<EvDataVO> newEvData(
		@RequestBody 
		EvDataVO dto
	) {
		EvDataVO vo = evDataService.newEvData(dto);
		if (vo != null) {
			return new ResponseEntity<>(vo, HttpStatus.CREATED);
		}
		throw new BadDataException("Data Already Exists");
	}
	
	@DeleteMapping("{vin}")
	public ResponseEntity<MessageDto> deleteEvData(@PathVariable String vin) {
		evDataService.deleteEvData(vin);
		MessageDto message = MessageDto.builder()
				.code(HttpStatus.OK.value()+"")
				.message("Successfully deleted")
				.build();
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
