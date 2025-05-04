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
public class CityList {
	private List<RefCity> list = new ArrayList<>();
	private Map<Long,RefCity> mapById;
	private Map<String,RefCity> mapByValue;
	@Autowired
	private CityRepository cityRepository;
	
	@PostConstruct
	public void load() {
		list= cityRepository.findAll();
		mapById = list.stream().collect(Collectors.toMap(RefCity::getId, Function.identity()));
		mapByValue = list.stream().collect(Collectors.toMap(RefCity::getCity, Function.identity()));
	}
	
	public String getValue(Long id) {
		RefCity ref = mapById.get(id);
		if(ref!=null) {
			return ref.getCity();
		}
		return null;
	}
	public Long getId(String value) {
		RefCity ref = mapByValue.get(value);
		if(ref!=null) {
			return ref.getId();
		}
		return null;
	}

}
