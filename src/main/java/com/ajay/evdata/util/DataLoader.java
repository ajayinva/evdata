package com.ajay.evdata.util;

import java.io.FileReader;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Component
public class DataLoader {//implements CommandLineRunner {

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private Map<String, Long> stateMap = new HashMap<>();
	private Map<String, Long> countyMap = new HashMap<>();
	private Map<String, Long> cityMap = new HashMap<>();
	private Map<String, Long> modelYearMap = new HashMap<>();
	private Map<String, Long> modelMap = new HashMap<>();
	private Map<String, Long> makeMap = new HashMap<>();
	private Map<String, Long> electricVehicleTypeMap = new HashMap<>();
	private Map<String, Long> cafvEligibilityMap = new HashMap<>();
	private Map<String, Long> electricUtilityMap = new HashMap<>();

	private Long stateId = 0L;
	private Long countyId = 0L;
	private Long cityId = 0L;
	private Long modelYearId = 0L;
	private Long modelId = 0L;
	private Long makeId = 0L;
	private Long electricVehicleTypeId = 0L;
	private Long cafvEligibilityId = 0L;
	private Long electricUtilityId = 0L;
	private static Long vinSuffix = 1000000L;

	private Long getElectricVehicleTypeId(String electricVehicleType) {
		if(StringUtils.isEmpty(electricVehicleType)) return null;
		if (electricVehicleTypeMap.get(electricVehicleType) == null) {
			electricVehicleTypeMap.put(electricVehicleType, ++electricVehicleTypeId);
		}
		return electricVehicleTypeMap.get(electricVehicleType);
	}

	private Long getCafvEligibilityId(String cafvEligibility) {
		if(StringUtils.isEmpty(cafvEligibility)) return null;
		if (cafvEligibilityMap.get(cafvEligibility) == null) {
			cafvEligibilityMap.put(cafvEligibility, ++cafvEligibilityId);
		}
		return cafvEligibilityMap.get(cafvEligibility);
	}

	private Long getElectricUtilityId(String electricUtility) {
		if(StringUtils.isEmpty(electricUtility)) return null;
		if (electricUtilityMap.get(electricUtility) == null) {
			electricUtilityMap.put(electricUtility, ++electricUtilityId);
		}
		return electricUtilityMap.get(electricUtility);
	}

	private Long getModelYearId(String modelYear) {
		if(StringUtils.isEmpty(modelYear)) return null;
		if (modelYearMap.get(modelYear) == null) {
			modelYearMap.put(modelYear, ++modelYearId);
		}
		return modelYearMap.get(modelYear);
	}

	private Long getModelId(String model) {
		if(StringUtils.isEmpty(model)) return null;
		if (modelMap.get(model) == null) {
			modelMap.put(model, ++modelId);
		}
		return modelMap.get(model);
	}

	private Long getMakeId(String make) {
		if(StringUtils.isEmpty(make)) return null;
		if (makeMap.get(make) == null) {
			makeMap.put(make, ++makeId);
		}
		return makeMap.get(make);
	}

	private Long getCountyId(String county) {
		if(StringUtils.isEmpty(county)) return null;
		if (countyMap.get(county) == null) {
			countyMap.put(county, ++countyId);
		}
		return countyMap.get(county);
	}

	private Long getCityId(String city) {
		if(StringUtils.isEmpty(city)) return null;
		if (cityMap.get(city) == null) {
			cityMap.put(city, ++cityId);
		}
		return cityMap.get(city);
	}

	private Long getStateId(String state) {
		if(StringUtils.isEmpty(state)) return null;
		if (stateMap.get(state) == null) {
			stateMap.put(state, ++stateId);
		}
		return stateMap.get(state);
	}

	public void load() throws Exception {
		Instant startTime = Instant.now();
		System.out.println("Started at::"+startTime);
		CSVReader reader = new CSVReaderBuilder(
				new FileReader("/Users/ajayagarwal/projects/evdata/Electric_Vehicle_Population_Data.csv"))
				.withSkipLines(1).build();
		String[] nextLine;
		SimpleJdbcInsert simpleJdbcInsert = 
				  new SimpleJdbcInsert(dataSource).withTableName("EVDATA");
		while ((nextLine = reader.readNext()) != null) {
			Map<String, Object> parameters = getParameters(buildRecord(nextLine));
			simpleJdbcInsert.execute(parameters);
		}

		insertState();
		insertCity();
		insertCounty();
		insertModelYear();
		insertModel();
		insertMake();
		insertElectricVehicleType();
		insertCafvEligibility();
		insertElectricUtility();
		Instant endTime = Instant.now();
		System.out.println("Stopped At::"+endTime);
		System.out.println("Time Taken::"+(endTime.getEpochSecond()-startTime.getEpochSecond()));
	}

	private Map<String, Object> getParameters(Record evdata) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("VIN", evdata.getVin()+(++vinSuffix));
		parameters.put("county_id", evdata.getCountyId());
		parameters.put("city_id", evdata.getCityId());
		parameters.put("state_id", evdata.getStateId());
		parameters.put("postal_code", evdata.getPostalCode());
		parameters.put("model_year_id", evdata.getModelYearId());
		parameters.put("make_id", evdata.getMakeId());
		parameters.put("model_id", evdata.getModelId());
		parameters.put("ev_type_id", evdata.getElectricVehicleTypeId());
		parameters.put("cafv_eligibility_id", evdata.getCafvEligibilityId());
		
		if(StringUtils.isEmpty(evdata.getElectricRange())){
			parameters.put("electric_range", null);
		} else {
			parameters.put("electric_range", evdata.getElectricRange());
		}
		
		if(StringUtils.isEmpty(evdata.getBaseMsrp())){
			parameters.put("base_msrp", null);
		} else {
			parameters.put("base_msrp", evdata.getBaseMsrp());
		}

		if(StringUtils.isEmpty(evdata.getLegislativeDistrict())){
			parameters.put("legislative_district", null);
		} else {
			parameters.put("legislative_district", evdata.getLegislativeDistrict());
		}
		
		parameters.put("dol_vehicle_id", evdata.getDolVehicleId());
		parameters.put("vehicle_location", evdata.getVehicleLocation());
		parameters.put("electric_utility", evdata.getElectricUtilityId());
		
		if(StringUtils.isEmpty(evdata.getCencusTract2020())){
			parameters.put("census_tract_2020", null);
		} else {
			parameters.put("census_tract_2020", evdata.getCencusTract2020());
		}
		
		return parameters;
		
	}

	private void insertElectricUtility() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("MSTELECTRICUTILITY");
		electricUtilityMap.forEach((k, v) -> {
			Map<String, Object> parameters = new HashMap<>(1);
			parameters.put("ELECTRIC_UTILITY", k);
			parameters.put("id", v);
			simpleJdbcInsert.execute(parameters);
		});
	}

	private void insertCafvEligibility() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("MSTCAFVELIGIBILITY");
		cafvEligibilityMap.forEach((k, v) -> {
			Map<String, Object> parameters = new HashMap<>(1);
			parameters.put("CAFV_ELIGIBILITY", k);
			parameters.put("id", v);
			simpleJdbcInsert.execute(parameters);
		});
	}

	private void insertElectricVehicleType() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("MSTEVTYPE");
		electricVehicleTypeMap.forEach((k, v) -> {
			Map<String, Object> parameters = new HashMap<>(1);
			parameters.put("EV_TYPE", k);
			parameters.put("id", v);
			simpleJdbcInsert.execute(parameters);
		});
	}

	private void insertMake() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("MSTMAKE");
		makeMap.forEach((k, v) -> {
			Map<String, Object> parameters = new HashMap<>(1);
			parameters.put("MAKE", k);
			parameters.put("id", v);
			simpleJdbcInsert.execute(parameters);
		});
	}

	private void insertModel() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("MSTMODEL");
		modelMap.forEach((k, v) -> {
			Map<String, Object> parameters = new HashMap<>(1);
			parameters.put("MODEL", k);
			parameters.put("id", v);
			simpleJdbcInsert.execute(parameters);
		});
	}

	private void insertModelYear() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("MSTMODELYEAR");
		modelYearMap.forEach((k, v) -> {
			Map<String, Object> parameters = new HashMap<>(1);
			parameters.put("YEAR", k);
			parameters.put("id", v);
			simpleJdbcInsert.execute(parameters);
		});
	}

	private void insertCounty() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("MSTCOUNTY");
		countyMap.forEach((k, v) -> {
			Map<String, Object> parameters = new HashMap<>(1);
			parameters.put("COUNTY", k);
			parameters.put("id", v);
			simpleJdbcInsert.execute(parameters);
		});
	}

	private void insertState() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("MSTSTATE");
		stateMap.forEach((k, v) -> {
			Map<String, Object> parameters = new HashMap<>(1);
			parameters.put("STATE", k);
			parameters.put("ID", v);
			simpleJdbcInsert.execute(parameters);
		});
	}

	private void insertCity() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("MSTCITY");
		cityMap.forEach((k, v) -> {
			Map<String, Object> parameters = new HashMap<>(1);
			parameters.put("CITY", k);
			parameters.put("id", v);
			simpleJdbcInsert.execute(parameters);
		});
	}

	private Record buildRecord(String[] values) {
		return Record.builder().vin(values[0]).county(values[1]).city(values[2]).state(values[3]).postalCode(values[4])
				.modelYear(values[5]).model(values[6]).make(values[7]).electricVehicleType(values[8])
				.cafvEligilibility(values[9]).electricRange(values[10]).baseMsrp(values[11])
				.legislativeDistrict(values[12]).dolVehicleId(values[13]).vehicleLocation(values[14])
				.electricUtility(values[15]).cencusTract2020(values[16]).countyId(getCountyId(values[1]))
				.cityId(getCityId(values[2])).stateId(getStateId(values[3])).modelYearId(getModelYearId(values[5]))
				.modelId(getModelId(values[6])).makeId(getMakeId(values[7]))
				.electricVehicleTypeId(getElectricVehicleTypeId(values[8]))
				.cafvEligibilityId(getCafvEligibilityId(values[9])).electricUtilityId(getElectricUtilityId(values[15]))
				.build();
	}
	public void run(String... args) throws Exception {
		this.load();
	}
}
