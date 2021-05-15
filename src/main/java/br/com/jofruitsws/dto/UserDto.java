package br.com.jofruitsws.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.jofruitsws.entity.Address;
import br.com.jofruitsws.entity.Phone;
import br.com.jofruitsws.entity.Sale;
import br.com.jofruitsws.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Integer id;
	private String cpf;
	private String name;
	private List<AddressDto> addressLstDto;
	private List<PhoneDto> phoneLstDto;
	private List<SaleDto> saleLstDto;

	public User transformaUser() {
		List<Address> addressLst = new ArrayList<Address>();
		if (addressLstDto != null) {
			for (AddressDto addressDto : addressLstDto) {
				addressLst.add(addressDto.transformaAddress());

			}
		}

		List<Phone> phoneLst = new ArrayList<Phone>();
		if (phoneLstDto != null) {
			for (PhoneDto phoneDto : phoneLstDto) {
				phoneLst.add(phoneDto.transformaPhone());
			}
		}

		List<Sale> saleLst = new ArrayList<Sale>();
		if (saleLstDto != null) {
			for (SaleDto saleDto : saleLstDto) {
				saleLst.add(saleDto.transformaSale());
			}
		}

		return new User(id, cpf, name, addressLst, phoneLst, saleLst);
	}

}
