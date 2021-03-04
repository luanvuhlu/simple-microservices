package com.luanvv.microservices.product.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "category", schema = "product")
public class Category {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private List<Product> products;
	
	@CreationTimestamp
	@Column(name = "created_time", updatable = false, nullable = true)
	private LocalDateTime createdTime;
	
	@UpdateTimestamp
	@Column(name = "updated_time", nullable = true)
	private LocalDateTime updatedTime;
	
	
	public Category(long id) {
		this.id = id;
	}

}
