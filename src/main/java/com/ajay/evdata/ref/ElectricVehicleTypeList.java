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
public class ElectricVehicleTypeList {
	private List<RefElectricVehicleType> list = new ArrayList<>();
	private Map<Long,RefElectricVehicleType> mapById;
	private Map<String,RefElectricVehicleType> mapByValue;
	@Autowired
	private ElectricVehicleTypeRepository repository;
	
	@PostConstruct
	public void load() {
		list= repository.findAll();
		mapById = list.stream().collect(Collectors.toMap(RefElectricVehicleType::getId, Function.identity()));
		mapByValue = list.stream().collect(Collectors.toMap(RefElectricVehicleType::getEvType, Function.identity()));
	}
	
	public String getValue(Long id) {
		RefElectricVehicleType ref = mapById.get(id);
		if(ref!=null) {
			return ref.getEvType();
		}
		return null;
	}
	public Long getId(String value) {
		RefElectricVehicleType ref = mapByValue.get(value);
		if(ref!=null) {
			return ref.getId();
		}
		return null;
	}

}
