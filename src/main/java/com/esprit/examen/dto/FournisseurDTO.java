package com.esprit.examen.dto;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.SecteurActivite;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FournisseurDTO implements Serializable {

	private static final long serialVersionUID = 1L;


	private Long idFournisseur;
	private String code;
	private String libelle;
	@Enumerated(EnumType.STRING)
	private CategorieFournisseur  categorieFournisseur;
	@JsonIgnore
	private Set<Facture> factures;
    @JsonIgnore
    private Set<SecteurActivite> secteurActivites;
    private DetailFournisseur detailFournisseur;
    

	
}
