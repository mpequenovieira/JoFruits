package br.com.jofruitsws.controller;

import java.util.ArrayList;
import java.util.List;
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

import br.com.jofruitsws.dto.UserDto;
import br.com.jofruitsws.entity.User;
import br.com.jofruitsws.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		try {
			Optional<User> usuario = userService.findById(id);
			if (usuario.isPresent()) {
				return new ResponseEntity<>(usuario.get().transformaUserDto(), HttpStatus.OK);
			}
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<?> listAll() {
		try {
			Iterable<User> users = userService.findAll();
			List<UserDto> usersDto = new ArrayList<UserDto>();
			for (User user : users) {
				usersDto.add(user.transformaUserDto());
			}
			return new ResponseEntity<>(usersDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_GATEWAY);
		}

	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody UserDto userDto) {
		try {
			limpaCpf(userDto);
			User user = userService.save(userDto.transformaUser());
			return new ResponseEntity<>(user.transformaUserDto(), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}

	}

	private void limpaCpf(UserDto userDto) {
		String cpf = userDto.getCpf();
		cpf = cpf.replace(".", "").replace("-", "");
		userDto.setCpf(cpf);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody UserDto userDto) {
		try {
			limpaCpf(userDto);
			User user = userService.save(userDto.transformaUser());
			return new ResponseEntity<>(user.transformaUserDto(), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try {
			Optional<User> usuario = userService.findById(id);
			userService.delete(usuario.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}
	}

}
