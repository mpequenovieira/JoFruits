package br.com.jofruitsws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jofruitsws.entity.Address;
import br.com.jofruitsws.entity.Sale;
import br.com.jofruitsws.entity.SaleProduct;
import br.com.jofruitsws.entity.User;
import br.com.jofruitsws.repository.SaleRepository;

@Service
@Transactional
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;
	@Autowired
	private AddressService addressService;
	@Autowired
	private UserService userService;
	@Autowired
	private SaleProductService saleProductService;
	@Autowired
	private ProductService productService;
	
	public Sale save(Sale sale) {
		User user;
		Address address;
		if(sale.getUser() != null && sale.getUser().getId() == null) {
			user = userService.save(sale.getUser());
			sale.setUser(user);
		}else {
			user = userService.findById(sale.getUser().getId()).get();
			sale.setUser(user);
		}
		if(sale.getAddress() != null && sale.getAddress().getId() == null ) {
			address= addressService.save(sale.getAddress());
			sale.setAddress(address);
		}else {
			address = addressService.findById(sale.getAddress().getId()).get();
			sale.setAddress(address);
		}
		List<SaleProduct> saleProductLst = sale.getSaleProductLst();
		Sale sa =  saleRepository.save(sale);
		sa.setSaleProductLst(new ArrayList<SaleProduct>());
		for(SaleProduct saleProduct : saleProductLst) {
			saleProduct.setProduct(productService.findById(saleProduct.getProduct().getId()).get());
			saleProduct.setSale(sa);
			saleProductService.save(saleProduct);
			sa.getSaleProductLst().add(saleProduct);
			
		}
		return sa;
	}
	
	public Optional<Sale> findById(Integer id) {
		return saleRepository.findById(id);
	}
	
	public void delete(Sale usuario) {
		saleRepository.delete(usuario);
	}
	
	public Iterable<Sale> findAll(){
		return saleRepository.findAll();
	}
}
