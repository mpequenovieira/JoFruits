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

import br.com.jofruitsws.dto.AddressDto;
import br.com.jofruitsws.entity.Address;
import br.com.jofruitsws.entity.User;
import br.com.jofruitsws.service.AddressService;
import br.com.jofruitsws.service.UserService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	private AddressService adreessService;
	@Autowired
	private UserService userService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		try {
			Optional<Address> address = adreessService.findById(id);
			if (address.isPresent()) {
				return new ResponseEntity<>(address.get().transformaAddressDto(), HttpStatus.OK);
			}
			return new ResponseEntity<>(address, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<?> listAll() {
		try {
			Iterable<Address> addressLst = adreessService.findAll();
			List<AddressDto> addressDtoLst = new ArrayList<AddressDto>();
			for (Address address : addressLst) {
				addressDtoLst.add(address.transformaAddressDto());
			}
			return new ResponseEntity<>(addressDtoLst, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_GATEWAY);
		}

	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody AddressDto addressDto) {
		try {
			Optional<User> user = userService.findById(addressDto.getUserId());
			Address address = addressDto.transformaAddress();
			address.setUser(user.get());
			address = adreessService.save(address);
			return new ResponseEntity<>(address.transformaAddressDto(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody AddressDto addressDto) {
		try {
			Optional<User> user = userService.findById(addressDto.getUserId());
			Address address = addressDto.transformaAddress();
			address.setUser(user.get());
			address = adreessService.save(address);
			return new ResponseEntity<>(address.transformaAddressDto(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try {
			Optional<Address> address = adreessService.findById(id);
			adreessService.delete(address.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}
	}

}
