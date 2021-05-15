package br.com.jofruitsws.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.jofruitsws.entity.Address;

public interface AddressRepository extends CrudRepository<Address, Integer>{

}
