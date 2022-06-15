package it.uniroma3.siw.catering.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.repository.IngredienteRepository;

@Service
public class IngredienteService {
	@Autowired
	private IngredienteRepository ingredienteRepo;
	@Transactional
	public void addIngrediente(Ingrediente ingrediente) {
		// TODO Auto-generated method stub
		this.ingredienteRepo.save(ingrediente);
	}
	public Ingrediente getIngrediente(Long idIngrediente) {
		return this.ingredienteRepo.findById(idIngrediente).get();
	}
	public Iterable<Ingrediente> findAll() {
		// TODO Auto-generated method stub
		return this.ingredienteRepo.findAll();
	}
	public boolean alreadyExists(Ingrediente ingrediente) {
		// TODO Auto-generated method stub
		return this.ingredienteRepo.existsByNomeAndOrigine(ingrediente.getNome(), ingrediente.getOrigine());
	}

}
