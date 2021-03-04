package com.luanvv.microservices.product.dto;

import java.time.LocalDateTime;

import com.luanvv.microservices.product.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
	private long id;
	private String name;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;
	private CategoryDTO category;

	public Product toBean() {
		return new Product(this.getId(), this.getName());
	}

	public static ProductDTO fromBean(Product product) {
		return new ProductDTO(product.getId(), product.getName(), product.getCreatedTime(), product.getUpdatedTime());
	}

	public ProductDTO(long id, String name, LocalDateTime createdTime, LocalDateTime updatedTime) {
		super();
		this.id = id;
		this.name = name;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

}
