package com.luanvv.microservices.product.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDTO {
	private long id;
	private String name;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;

}
