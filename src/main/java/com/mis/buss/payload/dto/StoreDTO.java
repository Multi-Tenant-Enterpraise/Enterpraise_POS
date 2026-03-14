package com.mis.buss.payload.dto;

import java.time.LocalDateTime;

import com.mis.buss.domain.StoreStatus;
import com.mis.buss.model.StoreContact;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class StoreDTO {
	
	private Long id;
	
	
	
	private String brand;
	
	
	private UserDto storeAdmin;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private String description;
	private String storeType;
	
	private StoreStatus status;
	
	private StoreContact contact;	
}
