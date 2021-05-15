package br.com.jofruitsws.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jofruitsws.entity.Product;
import br.com.jofruitsws.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	public Optional<Product> findById(Integer id) {
		return productRepository.findById(id);
	}
	
	public void delete(Product product) {
		productRepository.delete(product);
	}
	
	public Iterable<Product> findAll(){
		return productRepository.findAll();
	}
}
