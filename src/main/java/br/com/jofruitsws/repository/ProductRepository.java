package br.com.jofruitsws.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.jofruitsws.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
