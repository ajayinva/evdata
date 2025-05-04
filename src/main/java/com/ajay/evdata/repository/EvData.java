package com.ajay.evdata.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "EVDATA")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvData {
	@Id
	private String vin;
	private String postalCode;
	private Long electricRange;
	private Integer baseMsrp;
	private Long legislativeDistrict;
	private Long dolVehicleId;
	private String vehicleLocation;
	
	@Column(name = "census_tract_2020")
	private String censusTract2020;
	private Long countyId;
	private Long cityId;
	private Long stateId;
	private Long modelYearId;
	private Long modelId;
	private Long makeId;
	
	@Column(name = "ev_type_id")
	private Long electricVehicleTypeId;
	private Long cafvEligibilityId;
	
	@Column(name = "electric_utility")
	private Long electricUtilityId;
}
