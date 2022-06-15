package it.uniroma3.siw.catering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.catering.controller.validator.PiattoValidator;
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.services.IngredienteService;
import it.uniroma3.siw.catering.services.PiattoService;
import it.uniroma3.siw.catering.services.buffetService;

@Controller
public class PiattoController {
	@Autowired
	private PiattoService piattoService;
	@Autowired
	private IngredienteService ingredienteService;
	@Autowired
	private PiattoValidator piattoValidator;
	@Autowired
	private buffetService buffetService;

	@GetMapping(value ="admin/piattoForm")
	public String addPiatto(Model model) {
		model.addAttribute("piatto", new Piatto());
		System.out.println(ingredienteService.findAll());
		model.addAttribute("ingredienti",ingredienteService.findAll());
		model.addAttribute("piatti", piattoService.findAll());
		return "admin/piattoForm.html";
	}
	@PostMapping(value = "/piattoForm/adminPage" )
	public String addiPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult, @RequestParam(name="ingredientiSelezionati", required = false) List<Ingrediente> ingredienti, Model model) {
		piatto.setIngredienti(ingredienti);
		piattoValidator.validate(piatto,bindingResult);
		if(!bindingResult.hasErrors()) {
			this.piattoService.addPiatto(piatto);
			return "admin/adminPage.html";
		}
		//model.addAttribute("piatto", new Piatto());
		model.addAttribute("ingredienti",ingredienteService.findAll());
		return "admin/piattoForm.html";
	}
	@GetMapping(value = "/piatto/{id}")
	public String getPiatto(Model model, @PathVariable("id") Long id) {
		Piatto piatto = this.piattoService.getPiatto(id);
		List<Ingrediente> listaINgredienti = piatto.getIngredienti();
		model.addAttribute("piatto", piatto);
		model.addAttribute("ingredienti", listaINgredienti);
		return "piatto.html";
	}
	@GetMapping(value="/removePiatto/{id}")
	public String removePiatto(Model model, @PathVariable("id") Long id) {
		Piatto piatto = this.piattoService.findById(id);
		
		for(Buffet buffet:piatto.getBuffetsList()) {
			if(buffet.getPiatti().size() == 1)
				this.buffetService.remove(buffet.getId());
		}
		this.piattoService.remove(id);
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("ingredienti",this.ingredienteService.findAll());
		model.addAttribute("piatti", this.piattoService.findAll());
		return "admin/piattoForm.html";
	}
}
