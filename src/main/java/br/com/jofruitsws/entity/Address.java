package br.com.jofruitsws.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.jofruitsws.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	@Id
	@Column(name = "add_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Campo rua é obrigatório")
	private String street;

	@NotBlank(message = "Campo CEP é obrigatório")
	private String cep;

	@NotBlank(message = "Campo número é obrigatório")
	private String number;

	@NotBlank(message = "Campo bairro é obrigatório")
	private String district;

	@NotBlank(message = "Campo cidade é obrigatório")
	private String city;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference(value = "user-adderss")
	private User user;

	@OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Sale> saleLst;

	public AddressDto transformaAddressDto() {
		return new AddressDto(id, user.getId(), street, cep, number, district, city);
	}

}
