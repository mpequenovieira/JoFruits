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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.jofruitsws.dto.AddressDto;
import br.com.jofruitsws.dto.PhoneDto;
import br.com.jofruitsws.dto.SaleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
	
	@Id
	@Column(name = "phone_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Campo número é obrigatório")
	private String number;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference(value = "user-adderss")
	private User user;
	
	public PhoneDto transformaPhoneDto() {
		return new PhoneDto(id, number);
	}
	
}
