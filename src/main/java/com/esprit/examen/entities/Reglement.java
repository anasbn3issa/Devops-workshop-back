package com.esprit.examen.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.esprit.examen.dto.ReglementDTO;
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
public class Reglement implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReglement;
	private float montantPaye;
	private float montantRestant;
	private Boolean payee;
	@Temporal(TemporalType.DATE)
	private Date dateReglement;
	@ManyToOne
	@JsonIgnore
	private Facture facture;
	
	public Reglement(ReglementDTO reglementDTO) {
        this.idReglement = reglementDTO.getIdReglement();
        this.montantPaye = reglementDTO.getMontantPaye();
        this.montantRestant = reglementDTO.getMontantRestant();
        this.payee = reglementDTO.getPayee();
        this.dateReglement = reglementDTO.getDateReglement();
        this.facture = reglementDTO.getFacture();
	}
	
}
