package com.ajay.evdata.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EvDataVO {
	private String vin;
	private String postalCode;
	private String county;
	private String city;
	private String state;
	private String modelYear;
	private String model;
	private String make;
	private String electricVehicleType;
	private String cafvEligilibility;
	private Long electricRange;
	private Integer baseMsrp;
	private Long legislativeDistrict;
	private Long dolVehicleId;
	private String vehicleLocation;
	private String electricUtility;
	private String censusTract2020;
	private Long countyId;
	private Long cityId;
	private Long stateId;
	private Long modelYearId;
	private Long modelId;
	private Long makeId;
	private Long electricVehicleTypeId;
	private Long cafvEligibilityId;
	private Long electricUtilityId;
}
