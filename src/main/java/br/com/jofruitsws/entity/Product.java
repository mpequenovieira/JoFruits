package br.com.jofruitsws.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

	@Id
	@Column(name = "prod_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotBlank(message = "Campo nome é obrigatório")
	private String name;
	
	@NotNull(message = "Campo valor é obrigatório")
	private double value;
	
	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private List<SaleProduct> saleProductLst;
}
