package com.esprit.examen.dto;

import java.io.Serializable;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.esprit.examen.entities.Produit;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategorieProduitDTO implements Serializable {	
    private static final long serialVersionUID = 1L;

	private Long idCategorieProduit;
	private String codeCategorie;
	private String libelleCategorie;
	@JsonIgnore
	private Set<Produit> produits;
}
