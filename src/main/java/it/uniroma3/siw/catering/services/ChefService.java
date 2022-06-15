package it.uniroma3.siw.catering.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.ChefRepo;

@Service
public class ChefService {
	@Autowired
	private ChefRepo chefRepo;
	
	@Transactional
	public void addChef(Chef newChef) {
		this.chefRepo.save(newChef);
	}
	@Transactional
	public void setBuffetList(Chef chef, List<Buffet> buffetList) {
		this.chefRepo.findById(chef.getId()).get().setElencoBuffet(buffetList);
		this.chefRepo.save(chef);
	}
	public Iterable<Chef> findAll() {
		// TODO Auto-generated method stub
		return this.chefRepo.findAll();
	}
	public Chef findById(Long id) {
		// TODO Auto-generated method stub
		return this.chefRepo.findById(id).get();
	}
	public Chef getChef(Long id) {
		// TODO Auto-generated method stub
		return this.chefRepo.findById(id).get();
	}
	public boolean alreadyExists(Chef chef) {
		return this.chefRepo.existsByNomeAndCognomeAndNazionalita(chef.getNome(), chef.getCognome(), chef.getNazionalita());
	}
}
