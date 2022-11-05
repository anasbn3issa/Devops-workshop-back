package com.esprit.examen.dto;

import java.io.Serializable;
import java.util.Set;


import com.esprit.examen.entities.Facture;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperateurDTO implements Serializable{
	private static final long serialVersionUID = 1L;


	private Long idOperateur;
	private String nom;
	private String prenom;
	
	private String password;
	@JsonIgnore
	private Set<Facture> factures;
	
}
