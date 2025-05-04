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
public class ElectricUtilityList {
	private List<RefElectricUtility> list = new ArrayList<>();
	private Map<Long,RefElectricUtility> mapById;
	private Map<String,RefElectricUtility> mapByValue;
	@Autowired
	private ElectricUtilityRepository repository;
	
	@PostConstruct
	public void load() {
		list= repository.findAll();
		mapById = list.stream().collect(Collectors.toMap(RefElectricUtility::getId, Function.identity()));
		mapByValue = list.stream().collect(Collectors.toMap(RefElectricUtility::getElectricUtility, Function.identity()));
	}
	
	public String getValue(Long id) {
		RefElectricUtility ref = mapById.get(id);
		if(ref!=null) {
			return ref.getElectricUtility();
		}
		return null;
	}
	public Long getId(String value) {
		RefElectricUtility ref = mapByValue.get(value);
		if(ref!=null) {
			return ref.getId();
		}
		return null;
	}

}
