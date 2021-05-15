package br.com.jofruitsws.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.jofruitsws.dto.AddressDto;
import br.com.jofruitsws.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@CPF
	private String cpf;

	@NotNull
	@NotBlank(message = "Campo nome é obrigatório")
	private String name;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference(value = "user-adderss")
	private List<Address> adreessLst;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference(value = "user-phone")
	private List<Phone> phoneLst;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	@JsonBackReference(value = "user-sale")
	private List<Sale> saleLst;

	public UserDto transformaUserDto() {
		List<AddressDto> addressDtoLst = new ArrayList<AddressDto>();
		if(adreessLst != null){
			for(Address address : adreessLst) {
				addressDtoLst.add(address.transformaAddressDto());
			}
		}
		return new UserDto(id, cpf, name, addressDtoLst, null, null);
	}

}
