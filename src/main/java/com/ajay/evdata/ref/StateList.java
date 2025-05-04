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
public class StateList {
	private List<RefState> stateList = new ArrayList<>();
	private Map<Long,RefState> stateMapById;
	private Map<String,RefState> stateMapByValue;
	@Autowired
	private StateRepository stateRepository;
	
	@PostConstruct
	public void load() {
		stateList= stateRepository.findAll();
		stateMapById = stateList.stream().collect(Collectors.toMap(RefState::getId, Function.identity()));
		stateMapByValue = stateList.stream().collect(Collectors.toMap(RefState::getState, Function.identity()));
	}
	
	public String getValue(Long id) {
		RefState ref = stateMapById.get(id);
		if(ref!=null) {
			return ref.getState();
		}
		return null;
	}
	public Long getId(String value) {
		RefState ref = stateMapByValue.get(value);
		if(ref!=null) {
			return ref.getId();
		}
		return null;
	}

}
