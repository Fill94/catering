package it.uniroma3.siw.catering.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.repository.BuffetRepo;

@Service
public class buffetService {
	@Autowired
	private BuffetRepo DbRepo;
	
	public Iterable<Buffet> findAll(){
		return this.DbRepo.findAll();
	}
	@Transactional
	public Buffet findById(Long id) {
		return this.DbRepo.findById(id).get();
	}
	@Transactional
	public void addBuffet(Buffet newBuffet) {
		this.DbRepo.save(newBuffet);
	}
	public Chef getChef(Buffet buffet){
		return this.DbRepo.findById(buffet.getId()).get().getChef();
	}
	@Transactional
	public void setChef(Buffet buffet,Chef chef) {
		//this.DbRepo.findById(buffet.getId()).get().setChef(chef);
		buffet.setChef(chef);
		this.DbRepo.save(buffet);
	}
	@Transactional
	public void setPiatti(Buffet buffet, List<Piatto> piatti) {
		buffet.setPiatti(piatti);
		DbRepo.save(buffet);
	}
	public void remove(Long id) {
		this.DbRepo.deleteById(id);
	}
}
