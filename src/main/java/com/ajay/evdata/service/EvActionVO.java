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
public class EvActionVO {
	private String model;
	private String make;
	private Integer baseMsrp;
}
