package com.ajay.evdata.ref;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "MSTEVTYPE")
@NoArgsConstructor
@AllArgsConstructor
public class RefElectricVehicleType {
	@Id
	private Long id;
	private String evType;
}
