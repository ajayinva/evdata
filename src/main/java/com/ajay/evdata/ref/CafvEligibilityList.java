package com.ajay.evdata.ref;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class CafvEligibilityList {
	private List<RefCafvEligibility> list = new ArrayList<>();
	private Map<Long,RefCafvEligibility> mapById;
	private Map<String,RefCafvEligibility> mapByValue;
	@Autowired
	private CafvEligibilityRepository repository;
	
	@PostConstruct
	public void load() {
		list= repository.findAll();
		mapById = list.stream().collect(Collectors.toMap(RefCafvEligibility::getId, Function.identity()));
		mapByValue = list.stream().collect(Collectors.toMap(RefCafvEligibility::getCafvEligibility, Function.identity()));
	}
	
	public String getValue(Long id) {
		RefCafvEligibility ref = mapById.get(id);
		if(ref!=null) {
			return ref.getCafvEligibility();
		}
		return null;
	}
	public Long getId(String value) {
		RefCafvEligibility ref = mapByValue.get(value);
		if(ref!=null) {
			return ref.getId();
		}
		return null;
	}

}
