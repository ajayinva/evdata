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
public class CountyList {
	private List<RefCounty> list = new ArrayList<>();
	private Map<Long,RefCounty> mapById;
	private Map<String,RefCounty> mapByValue;
	@Autowired
	private CountyRepository repository;
	
	@PostConstruct
	public void load() {
		list= repository.findAll();
		mapById = list.stream().collect(Collectors.toMap(RefCounty::getId, Function.identity()));
		mapByValue = list.stream().collect(Collectors.toMap(RefCounty::getCounty, Function.identity()));
	}
	
	public String getValue(Long id) {
		RefCounty ref = mapById.get(id);
		if(ref!=null) {
			return ref.getCounty();
		}
		return null;
	}
	public Long getId(String value) {
		RefCounty ref = mapByValue.get(value);
		if(ref!=null) {
			return ref.getId();
		}
		return null;
	}

}
