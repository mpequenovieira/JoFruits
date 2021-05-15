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

import br.com.jofruitsws.entity.Sale;
import br.com.jofruitsws.service.SaleService;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

	@Autowired
	private SaleService saleService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		try {
			Optional<Sale> usuario = saleService.findById(id);
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> listAll(){
		try {
			return new ResponseEntity<> (saleService.findAll(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> ("Exception:"+e,HttpStatus.BAD_GATEWAY);
		}
		
	}

	@PostMapping
	public ResponseEntity<?> save( @RequestBody Sale sale) {
		try {
			sale = saleService.save(sale);
			return new ResponseEntity<>(sale, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}

	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Sale sale){
		try {
			return new ResponseEntity<>(saleService.save(sale), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		try {
			Optional<Sale> sale = saleService.findById(id);
			saleService.delete(sale.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}
	}

}
