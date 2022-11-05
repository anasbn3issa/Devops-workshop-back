package com.esprit.examen.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.esprit.examen.dto.SecteurActiviteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SecteurActivite implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSecteurActivite;
	private String codeSecteurActivite;
	private String libelleSecteurActivite;
	@ManyToMany(mappedBy="secteurActivites")
	@JsonIgnore
	private Set<Fournisseur> fournisseurs;
	
	public SecteurActivite(SecteurActiviteDTO secteurActiviteDTO) {
        this.idSecteurActivite = secteurActiviteDTO.getIdSecteurActivite();
        this.codeSecteurActivite = secteurActiviteDTO.getCodeSecteurActivite();
        this.libelleSecteurActivite = secteurActiviteDTO.getLibelleSecteurActivite();
        this.fournisseurs = secteurActiviteDTO.getFournisseurs();
	}
}
