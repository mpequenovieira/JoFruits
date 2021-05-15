package br.com.jofruitsws.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jofruitsws.entity.Address;
import br.com.jofruitsws.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	public Address save(Address address) {
		return addressRepository.save(address);
	}
	
	public Optional<Address> findById(Integer id) {
		return addressRepository.findById(id);
	}
	
	public void delete(Address  address) {
		addressRepository.delete(address);
	}
	
	public Iterable<Address> findAll(){
		return addressRepository.findAll();
	}
}
