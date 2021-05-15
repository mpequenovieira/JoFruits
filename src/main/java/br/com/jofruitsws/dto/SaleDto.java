package br.com.jofruitsws.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.jofruitsws.entity.Sale;
import br.com.jofruitsws.entity.SaleProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDto {
	
	private Integer id;
	private AddressDto addressDto;
	private List<ProductDto> productDtoLst;
	

	public Sale transformaSale() {
		List<SaleProduct> saleProductLst = new ArrayList<SaleProduct>();
		for(ProductDto productDto : productDtoLst) {
			//saleProductLst.add(new SaleProduct(productDto.getId(), null, null, 0));
		}
		return null;
	}

}
