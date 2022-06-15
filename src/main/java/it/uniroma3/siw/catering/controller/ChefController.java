package it.uniroma3.siw.catering.controller;

import java.util.ArrayList;
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

import it.uniroma3.siw.catering.controller.validator.ChefValidator;
import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.services.ChefService;

@Controller
public class ChefController {
	@Autowired
	private ChefService chefService;
	@Autowired
	private ChefValidator chefValidator;
	
	@PostMapping(value="/cuocoForm/adminPage")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult) {
		chefValidator.validate(chef, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.chefService.addChef(chef);
			return "admin/adminPage.html";
		}
		else {
			return "admin/cuocoForm.html";
		}
	}
	
	@GetMapping(value="/admin/cuocoForm")
	public String addChef(Model model) {
		model.addAttribute("chef",new Chef());
		return "admin/cuocoForm.html";
	}
	@GetMapping(value = "/chefList")
	public String elncoChef(Model model) {
		model.addAttribute("chefs", this.chefService.findAll());
		return "chefList.html";
	}

	@GetMapping(value="/chef/{id}")
	public String getChef(Model model, @PathVariable("id") Long id) {
		Chef chef = this.chefService.findById(id);
		model.addAttribute("chef",chef);
		model.addAttribute("buffets", chef.getElencoBuffet());
		return "chef.html";
	}
	public void setChef(Chef chef, Buffet buffet) {
		List<Buffet> listaBuffet = new ArrayList<>();
		listaBuffet.add(buffet);
		this.chefService.setBuffetList(chef, listaBuffet);
		
	}
}
