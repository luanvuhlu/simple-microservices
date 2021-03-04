package com.luanvv.microservices.product.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luanvv.microservices.product.dto.ProductDTO;
import com.luanvv.microservices.product.entities.Category;
import com.luanvv.microservices.product.entities.Product;
import com.luanvv.microservices.product.exceptions.ProductNotFoundException;
import com.luanvv.microservices.product.repositories.ProductRepository;
import com.luanvv.microservices.product.repositories.ReadProductRepository;

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ReadProductRepository readProductRepository;

	@GetMapping("/products")
	public Iterable<ProductDTO> retrieveAll(@RequestParam("page") int page, @RequestParam("size") int size) {
		Page<Product> pageResult = readProductRepository.findAll(PageRequest.of(page, size));
		List<ProductDTO> content = pageResult.getContent()
				.stream()
				.map(ProductDTO::fromBean)
				.collect(Collectors.toList());
		return new PageImpl<>(content, PageRequest.of(page, size), pageResult.getTotalElements());
	}

	@GetMapping("/product/{id}")
	public ProductDTO retrieve(@PathVariable long id) {
		return readProductRepository.findById(id)
				.map(ProductDTO::fromBean)
				.orElseThrow(() -> new ProductNotFoundException("id-" + id));
	}

	@GetMapping("/products/category/{id}")
	public Iterable<ProductDTO> retrieveByCategoryId(@PathVariable long id, 
			@RequestParam("page") int page, 
			@RequestParam("size") int size) {
		 Page<Product> pageResult = readProductRepository.findByCategory(new Category(id), PageRequest.of(page, size));
		 List<ProductDTO> content = pageResult.getContent()
					.stream()
					.map(ProductDTO::fromBean)
					.collect(Collectors.toList());
			return new PageImpl<>(content, PageRequest.of(page, size), pageResult.getTotalElements());
	}
	
	@DeleteMapping("/product/{id}")
	public void delete(@PathVariable long id) {
		productRepository.deleteById(id);
	}

	@PostMapping("/product")
	public ResponseEntity<Object> create(@RequestBody ProductDTO productDTO) {
		Product product = productRepository.save(productDTO.toBean());
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(product.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}

	@PutMapping("/product/{id}")
	public ResponseEntity<Object> update(@RequestBody ProductDTO productDTO, @PathVariable long id) {
		Optional<Product> productOptional = productRepository.findById(id);
		if (!productOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		productDTO.setId(id);
		productRepository.save(productDTO.toBean());
		return ResponseEntity.noContent().build();
	}
}
