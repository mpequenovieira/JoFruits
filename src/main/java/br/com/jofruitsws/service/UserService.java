package br.com.jofruitsws.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jofruitsws.entity.User;
import br.com.jofruitsws.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}
	
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	public Iterable<User> findAll(){
		return userRepository.findAll();
	}
}
