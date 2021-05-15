package br.com.jofruitsws.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jofruitsws.entity.Phone;
import br.com.jofruitsws.entity.User;
import br.com.jofruitsws.service.PhoneService;
import br.com.jofruitsws.service.UserService;

@RestController
@RequestMapping("/api/phone")
public class PhoneController {
	
	@Autowired
	private PhoneService phoneService;
	@Autowired
	private UserService userService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		try {
			Optional<Phone> usuario = phoneService.findById(id);
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> listAll(){
		try {
			return new ResponseEntity<> (phoneService.findAll(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> ("Exception:"+e,HttpStatus.BAD_GATEWAY);
		}
		
	}

	@PostMapping
	public ResponseEntity<?> save( @RequestBody Phone phone) {
		try {
			Optional<User> user = userService.findById(phone.getUser().getId());
			phone.setUser(user.get());
			phone = phoneService.save(phone);
			return new ResponseEntity<>(phone, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}

	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Phone phone){
		try {
			Optional<User> user = userService.findById(phone.getUser().getId());
			phone.setUser(user.get());
			return new ResponseEntity<>(phoneService.save(phone), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		try {
			Optional<Phone> phone = phoneService.findById(id);
			phoneService.delete(phone.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}
	}
}
