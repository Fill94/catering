package it.uniroma3.siw.catering.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.repository.PiattoRepository;

@Service
public class PiattoService {
	@Autowired
	private PiattoRepository piattoRepo;
	
	@Transactional
	public void addPiatto(Piatto piatto) {
		this.piattoRepo.save(piatto);
	}
	
	public Piatto getPiatto(Long id) {
		return this.piattoRepo.findById(id).get();
	}
	public Iterable<Piatto> findAll(){
		return this.piattoRepo.findAll();
	}

	public void remove(Long id) {
		this.piattoRepo.deleteById(id);
	}

	public Piatto findById(Long id) {
		// TODO Auto-generated method stub
		return this.piattoRepo.findById(id).get();
	}
	

}
