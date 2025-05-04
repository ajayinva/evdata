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
public class ModelList {
	private List<RefModel> list = new ArrayList<>();
	private Map<Long,RefModel> mapById;
	private Map<String,RefModel> mapByValue;
	@Autowired
	private ModelRepository repository;
	
	@PostConstruct
	public void load() {
		list= repository.findAll();
		mapById = list.stream().collect(Collectors.toMap(RefModel::getId, Function.identity()));
		mapByValue = list.stream().collect(Collectors.toMap(RefModel::getModel, Function.identity()));
	}
	
	public String getValue(Long id) {
		RefModel ref = mapById.get(id);
		if(ref!=null) {
			return ref.getModel();
		}
		return null;
	}
	public Long getId(String value) {
		RefModel ref = mapByValue.get(value);
		if(ref!=null) {
			return ref.getId();
		}
		return null;
	}

}
