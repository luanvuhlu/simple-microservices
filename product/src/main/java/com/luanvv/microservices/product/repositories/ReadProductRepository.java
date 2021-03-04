package com.luanvv.microservices.product.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.luanvv.microservices.product.configuration.datasource.ReadOnlyRepository;
import com.luanvv.microservices.product.entities.Category;
import com.luanvv.microservices.product.entities.Product;

@ReadOnlyRepository
public interface ReadProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findByCategory(Category category, Pageable pageable);
}
