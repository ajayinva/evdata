package com.ajay.evdata.util;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class Record {
	private String vin;
	private String county;
	private String city;
	private String state;
	private String postalCode;
	private String modelYear;
	private String model;
	private String make;
	private String electricVehicleType;
	private String cafvEligilibility;
	private String electricRange;
	private String baseMsrp;
	private String legislativeDistrict;
	private String dolVehicleId;
	private String vehicleLocation;
	private String electricUtility;
	private String cencusTract2020;
	
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
