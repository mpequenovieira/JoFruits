package br.com.jofruitsws.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.jofruitsws.entity.Sale;

public interface SaleRepository extends CrudRepository<Sale, Integer> {

}
