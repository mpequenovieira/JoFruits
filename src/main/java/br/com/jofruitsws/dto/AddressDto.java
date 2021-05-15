package br.com.jofruitsws.dto;

import br.com.jofruitsws.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

	private Integer id;
	private Integer userId;
	private String street;
	private String cep;
	private String number;
	private String district;
	private String city;

	public Address transformaAddress() {
		return new Address(id, street, cep, number, district, city, null, null);
	}

}
