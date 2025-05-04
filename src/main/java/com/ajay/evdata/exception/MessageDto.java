package com.ajay.evdata.exception;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {
	@Builder.Default
	private Date timestamp = new Date();
	private String code;
	private String message;
}
