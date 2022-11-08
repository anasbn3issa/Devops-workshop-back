package com.esprit.examen.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.ReglementRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReglementServiceImpl implements IReglementService {

	@Autowired
	FactureRepository factureRepository;
	@Autowired
	ReglementRepository reglementRepository;

	@Override
	public List<Reglement> retrieveAllReglements() {
		log.info("In method retrieveAllReglements");
		return (List<Reglement>) reglementRepository.findAll();
	}

	@Override
	public Reglement addReglement(Reglement r) {
		log.info("In method addReglement");
		reglementRepository.save(r);
		return r;
	}

	@Override
	public Reglement retrieveReglement(Long id) {
		log.info("In method retrieveReglement");
		return reglementRepository.findById(id).orElse(null);
	}

	@Override
	public List<Reglement> retrieveReglementByFacture(Long idFacture) {
		log.info("In method retrieveReglementByFacture");
		return reglementRepository.retrieveReglementByFacture(idFacture);

	}

	@Override
	public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
		log.info("In method getChiffreAffaireEntreDeuxDate");
		return reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate);
	}

	@Override
	public void deleteReglement(Long reglementId) {
		log.info("In method deleteStock");
		reglementRepository.deleteById(reglementId);

	}

}
