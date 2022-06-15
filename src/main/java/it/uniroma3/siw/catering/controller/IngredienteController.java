package it.uniroma3.siw.catering.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.catering.controller.validator.IngredienteValidator;
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.services.IngredienteService;

@Controller
public class IngredienteController {
	@Autowired
	private IngredienteService ingredienteService;
	@Autowired
	private IngredienteValidator validator;
	
	@PostMapping(value = "/ingredienteForm/adminPage")
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult) {
		validator.validate(ingrediente, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.ingredienteService.addIngrediente(ingrediente);
			return "admin/adminPage.html";
		}
		else
			return "admin/ingredienteForm.html";
	}
	
	@GetMapping(value = "admin/ingredienteForm")
	public String addIngrediente(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "admin/ingredienteForm.html";
	}
	@GetMapping(value = "/ingrediente/{id}")
	public String getIngrediente(@PathVariable("id") Long idIngrediente, Model model) {
		model.addAttribute("ingrediente", this.ingredienteService.getIngrediente(idIngrediente));
		return "ingrediente.html";
	}
}
