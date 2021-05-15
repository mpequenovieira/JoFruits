package br.com.jofruitsws.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jofruitsws.entity.Phone;
import br.com.jofruitsws.repository.PhoneRepository;

@Service
public class PhoneService {
	
	@Autowired
	private PhoneRepository phoneRepository;

	public Phone save(Phone phone) {
		return phoneRepository.save(phone);
	}

	public Optional<Phone> findById(Integer id) {
		return phoneRepository.findById(id);
	}

	public void delete(Phone phone) {
		phoneRepository.delete(phone);
	}

	public Iterable<Phone> findAll() {
		return phoneRepository.findAll();
	}
}
