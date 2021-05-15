package br.com.jofruitsws.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jofruitsws.entity.SaleProduct;
import br.com.jofruitsws.repository.SaleProductRepository;

@Service
public class SaleProductService {

	@Autowired
	private SaleProductRepository saleProductRepository;
	
	public SaleProduct save(SaleProduct saleProduct) {
		return saleProductRepository.save(saleProduct);
	}
	
	public Optional<SaleProduct> findById(Integer id) {
		return saleProductRepository.findById(id);
	}
	
	public void delete(SaleProduct saleProduct) {
		saleProductRepository.delete(saleProduct);
	}
	
	public Iterable<SaleProduct> findAll(){
		return saleProductRepository.findAll();
	}

}
