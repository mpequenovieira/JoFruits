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

import br.com.jofruitsws.entity.Product;
import br.com.jofruitsws.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		try {
			Optional<Product> usuario = productService.findById(id);
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> listAll(){
		try {
			return new ResponseEntity<> (productService.findAll(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> ("Exception:"+e,HttpStatus.BAD_GATEWAY);
		}
		
	}

	@PostMapping
	public ResponseEntity<?> save( @RequestBody Product product) {
		try {
			product = productService.save(product);
			return new ResponseEntity<>(product, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}

	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Product product){
		try {
			return new ResponseEntity<>(productService.save(product), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		try {
			Optional<Product> product = productService.findById(id);
			productService.delete(product.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception:" + e, HttpStatus.BAD_REQUEST);
		}
	}

}
