package com.esprit.examen.dto;

import java.io.Serializable;
import java.util.Set;


import com.esprit.examen.entities.Produit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockDTO implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long idStock;
	private String libelleStock;
	private Integer qte;
	private Integer qteMin;
	@JsonIgnore
	private Set<Produit> produits;
	public StockDTO(String libelleStock, Integer qte, Integer qteMin) {
		super();
		this.libelleStock = libelleStock;
		this.qte = qte;
		this.qteMin = qteMin;
	}
}
