package com.esprit.examen.dto;

import java.io.Serializable;
import java.util.Set;


import com.esprit.examen.entities.Fournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SecteurActiviteDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idSecteurActivite;
	private String codeSecteurActivite;
	private String libelleSecteurActivite;
	@JsonIgnore
	private Set<Fournisseur> fournisseurs;
}
