package com.luanvv.microservices.product.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product", schema = "product")
public class Product {

	@Id
	@GeneratedValue
	private long id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = true)
	private Category category;
	
	@CreationTimestamp
	@Column(name = "created_time", updatable = false, nullable = true)
	private LocalDateTime createdTime;
	
	@UpdateTimestamp
	@Column(name = "updated_time", nullable = true)
	private LocalDateTime updatedTime;

	public Product(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
}
