package br.com.jofruitsws.dto;

import br.com.jofruitsws.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {
	
	private Integer id;
	private String number;

	public Phone transformaPhone() {
		
		return new Phone(id, number, null);
	}

}
