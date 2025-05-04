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
public class MakeList {
	private List<RefMake> list = new ArrayList<>();
	private Map<Long,RefMake> mapById;
	private Map<String,RefMake> mapByValue;
	@Autowired
	private MakeRepository repository;
	
	@PostConstruct
	public void load() {
		list= repository.findAll();
		mapById = list.stream().collect(Collectors.toMap(RefMake::getId, Function.identity()));
		mapByValue = list.stream().collect(Collectors.toMap(RefMake::getMake, Function.identity()));
	}
	
	public String getValue(Long id) {
		RefMake ref = mapById.get(id);
		if(ref!=null) {
			return ref.getMake();
		}
		return null;
	}
	public Long getId(String value) {
		RefMake ref = mapByValue.get(value);
		if(ref!=null) {
			return ref.getId();
		}
		return null;
	}

}
