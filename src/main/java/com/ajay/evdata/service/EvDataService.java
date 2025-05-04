package com.ajay.evdata.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ajay.evdata.ref.CafvEligibilityList;
import com.ajay.evdata.ref.CityList;
import com.ajay.evdata.ref.CountyList;
import com.ajay.evdata.ref.ElectricUtilityList;
import com.ajay.evdata.ref.ElectricVehicleTypeList;
import com.ajay.evdata.ref.MakeList;
import com.ajay.evdata.ref.ModelList;
import com.ajay.evdata.ref.ModelYearList;
import com.ajay.evdata.ref.StateList;
import com.ajay.evdata.repository.EvData;
import com.ajay.evdata.repository.EvDataRepository;

@Service
public class EvDataService {
	
	@Autowired
	private StateList stateList;
	
	@Autowired
	private CityList cityList;
	
	@Autowired
	private CountyList countyList;
	
	@Autowired
	private ModelYearList modelYearList;
	
	@Autowired
	private ModelList modelList;
	
	@Autowired
	private MakeList makeList;
	
	@Autowired
	private ElectricVehicleTypeList electricVehicleTypeList;
	
	@Autowired
	private CafvEligibilityList cafvEligibilityList;
	
	@Autowired
	private ElectricUtilityList electricUtilityList;
	
	@Autowired
	public EvDataRepository evDataRepository;
	
	
	public List<EvDataVO> getAllEvData() {
		Page<EvData> pageData = evDataRepository.findAll(Pageable.ofSize(10));
		List<EvDataVO> dataList = new ArrayList<>();
		pageData.forEach(data->dataList.add(this.toVO(data)));
		return dataList;
	}
	
	
	public EvDataVO getEvData(String vin) {
		Optional<EvData> data = evDataRepository.findById(vin);
		if(data.isPresent()) {
			return this.toVO(data.get());
		}
		return null;
	}
	
	public void deleteEvData(String vin) {
		evDataRepository.deleteById(vin);
	}
	
	public EvDataVO updateEvData(EvDataVO dto) {
		Optional<EvData> data = evDataRepository.findById(dto.getVin());
		if(data.isPresent()) {
			EvData inputData = this.toEntity(dto);
			EvData outputData = evDataRepository.save(inputData);
			return this.toVO(outputData);
		}
		return null;
	}
	
	
	public EvDataVO newEvData(EvDataVO dto) {
		Optional<EvData> data = evDataRepository.findById(dto.getVin());
		if(!data.isPresent()) {
			EvData inputData = this.toEntity(dto);
			EvData outputData = evDataRepository.save(inputData);
			return this.toVO(outputData);
		}
		return null;		
	}
	
	private EvData toEntity(EvDataVO dto) {
		return EvData.builder()
				.vin(dto.getVin())
				.postalCode(dto.getPostalCode())
				.electricRange(dto.getElectricRange())
				.legislativeDistrict(dto.getLegislativeDistrict())
				.dolVehicleId(dto.getDolVehicleId())
				.vehicleLocation(dto.getVehicleLocation())
				.censusTract2020(dto.getCensusTract2020())
				.countyId(dto.getCountyId())
				.cityId(dto.getCityId())
				.stateId(dto.getStateId())
				.modelYearId(dto.getModelYearId())
				.modelId(dto.getModelId())
				.makeId(dto.getMakeId())
				.electricVehicleTypeId(dto.getElectricVehicleTypeId())
				.cafvEligibilityId(dto.getCafvEligibilityId())
				.electricUtilityId(dto.getElectricUtilityId())
				.baseMsrp(dto.getBaseMsrp())
				.build();
				
	}
	
	
	private EvDataVO toVO(EvData entity) {
		return EvDataVO.builder()
				.vin(entity.getVin())
				.postalCode(entity.getPostalCode())
				.county(countyList.getValue(entity.getCountyId()))
				.city(cityList.getValue(entity.getCityId()))
				.state(stateList.getValue(entity.getStateId()))
				.modelYear(modelYearList.getValue(entity.getModelYearId()))
				.model(modelList.getValue(entity.getModelId()))
				.make(makeList.getValue(entity.getMakeId()))
				.electricVehicleType(electricVehicleTypeList.getValue(entity.getElectricVehicleTypeId()))
				.cafvEligilibility(cafvEligibilityList.getValue(entity.getCafvEligibilityId()))
				.electricRange(entity.getElectricRange())
				.legislativeDistrict(entity.getLegislativeDistrict())
				.dolVehicleId(entity.getDolVehicleId())
				.vehicleLocation(entity.getVehicleLocation())
				.electricUtility(electricUtilityList.getValue(entity.getElectricUtilityId()))
				.censusTract2020(entity.getCensusTract2020())
				.countyId(entity.getCountyId())
				.cityId(entity.getCityId())
				.stateId(entity.getStateId())
				.modelYearId(entity.getModelYearId())
				.modelId(entity.getModelId())
				.makeId(entity.getMakeId())
				.electricVehicleTypeId(entity.getElectricVehicleTypeId())
				.cafvEligibilityId(entity.getCafvEligibilityId())
				.electricUtilityId(entity.getElectricUtilityId())
				.baseMsrp(entity.getBaseMsrp())
				.build();
				
	}
	
	public void update(EvActionVO dto) {
		Long makeId = makeList.getId(dto.getMake());
		Long modelId = modelList.getId(dto.getModel());
		evDataRepository.updateMsrp(makeId, modelId,dto.getBaseMsrp());
	}
}
