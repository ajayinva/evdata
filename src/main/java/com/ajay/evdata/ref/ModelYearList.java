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
public class ModelYearList {
	private List<RefModelYear> list = new ArrayList<>();
	private Map<Long,RefModelYear> mapById;
	private Map<String,RefModelYear> mapByValue;
	@Autowired
	private ModelYearRepository repository;
	
	@PostConstruct
	public void load() {
		list= repository.findAll();
		mapById = list.stream().collect(Collectors.toMap(RefModelYear::getId, Function.identity()));
		mapByValue = list.stream().collect(Collectors.toMap(RefModelYear::getYear, Function.identity()));
	}
	
	public String getValue(Long id) {
		RefModelYear ref = mapById.get(id);
		if(ref!=null) {
			return ref.getYear();
		}
		return null;
	}
	public Long getId(String value) {
		RefModelYear ref = mapByValue.get(value);
		if(ref!=null) {
			return ref.getId();
		}
		return null;
	}

}
