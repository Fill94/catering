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
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.catering.controller.validator.BuffetValidator;
import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.services.ChefService;
import it.uniroma3.siw.catering.services.PiattoService;
import it.uniroma3.siw.catering.services.buffetService;

@Controller
public class BuffetController {
	@Autowired
	private buffetService buffetService;
	@Autowired
	private ChefService chefService;
	@Autowired
	private PiattoService piattoService;
	@Autowired
	private BuffetValidator buffetValidator;

	@GetMapping(value="/buffetList")
	public String elencoBuffet(Model model) {
		model.addAttribute("buffets", buffetService.findAll());
		return "buffetList.html";
	}
	@PostMapping(value="/buffetForm/adminPage")
//	public String addBuffet(@ModelAttribute("buffet") @Valid Buffet buffet, BindingResult bindingResult, @RequestParam(name="chefSelezionato", required=false)  Long chefId, @RequestParam(name="piattiSelezionati", required = false)  List<Piatto> listaPiatti,Model model){
//		//IL BUFFET VIENE COLLEGATO ALLO CHEF
//		Chef chef = null;
//		if(chefId != null) {
//			chef = this.chefService.findById(chefId);
//		}
//		buffet.setChef(chef);
//		//IL BUFFET VIENE COLLEGATO AI PIATTI--- viene fatto setPiatti() secco perchè il buffet non ha ancora una lista piatti
//		buffet.setPiatti(listaPiatti);
//		this.buffetValidator.validate(buffet, bindingResult);
//		if(!bindingResult.hasErrors()) {
//
//
//			//LO CHEF VIENE COLLEGATO AL BUFFET
//			if(chef.getElencoBuffet() != null)
//				chef.getElencoBuffet().add(buffet);
//			else {
//
//				List<Buffet> listaBuffet = new ArrayList<Buffet>();
//				listaBuffet.add(buffet);
//				chef.setElencoBuffet(listaBuffet);
//			}
//
//			//OGNI PIATTO VIENE COLLEGATO AL BUFFET---controllo che il piatto abbia altri buffet collegati, se sì aggiungo il buffet alla lista buffet del piatto, altrimenti creo una lista di buffet e la aggiungo al piatto
//			for(Piatto piatto : listaPiatti) {
//				if(piatto.getBuffetsList() != null) {
//					piatto.getBuffetsList().add(buffet);
//				}
//				else {
//					List<Buffet> buffetList = new ArrayList<Buffet>();
//					buffetList.add(buffet);
//					piatto.setBuffetsList(buffetList);
//				}
//				this.piattoService.addPiatto(piatto);
//			}
//			//IL BUFFET E LO CHEF VENGONO SALVATI NEL DB
//			this.buffetService.addBuffet(buffet);
//			this.chefService.addChef(chef);
//			return "admin/adminPage.html";
//		}
//		else {
//			model.addAttribute("chefs", this.chefService.findAll());
//			model.addAttribute("piatti", this.piattoService.findAll());
//			return "admin/buffetForm.html";
//		}
//
//	}
	public String addBuffet(@ModelAttribute("buffet") @Valid Buffet buffet, BindingResult bindingResult, @RequestParam(name="chefSelezionato", required=false)  Long chefId, @RequestParam(name="piattiSelezionati", required = false)  List<Piatto> listaPiatti,Model model){
	//IL BUFFET VIENE COLLEGATO ALLO CHEF
	Chef chef = null;
	if(chefId != null) {
		chef = this.chefService.findById(chefId);
	}
	buffet.setChef(chef);
	//IL BUFFET VIENE COLLEGATO AI PIATTI--- viene fatto setPiatti() secco perchè il buffet non ha ancora una lista piatti
	buffet.setPiatti(listaPiatti);
	this.buffetValidator.validate(buffet, bindingResult);
	if(!bindingResult.hasErrors()) {

		//IL BUFFET E LO CHEF VENGONO SALVATI NEL DB
		this.buffetService.addBuffet(buffet);
		return "admin/adminPage.html";
	}
	else {
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("piatti", this.piattoService.findAll());
		return "admin/buffetForm.html";
	}

}	
	

	@PostMapping(value = "/updateBuffetForm/{id}/adminPage")
//	public String updateBuffet(@ModelAttribute("buffet") @Valid Buffet buffet, BindingResult bindingResult,@RequestParam(name="piattiSelezionati", required = false) List<Piatto> listaPiatti,@RequestParam(name="chefSelezionato", required = false) Long chefId) {
//		Chef chef = null;
//		if(chefId != null)
//			chef = this.chefService.findById(chefId);
//		buffet.setChef(chef);
//		buffet.setPiatti(listaPiatti);
//		this.buffetValidator.validate(buffet, bindingResult);
//		if(!bindingResult.hasErrors()) {
//			if(chef.getElencoBuffet()!=null)
//				chef.getElencoBuffet().add(buffet);
//			else {
//				List<Buffet> elencoBuffet = new ArrayList<Buffet>();
//				elencoBuffet.add(buffet);
//				chef.setElencoBuffet(elencoBuffet);
//			}
//			for(Piatto piatto : listaPiatti) {
//				if(piatto.getBuffetsList() != null) {
//					piatto.getBuffetsList().add(buffet);
//				}
//				else {
//					List<Buffet> buffetList = new ArrayList<Buffet>();
//					buffetList.add(buffet);
//					piatto.setBuffetsList(buffetList);
//				}
//				this.piattoService.addPiatto(piatto);
//			}
//			//IL BUFFET E LO CHEF VENGONO SALVATI NEL DB
//			this.buffetService.addBuffet(buffet);
//			this.chefService.addChef(chef);
//			return "admin/adminPage.html";
//		}
//		return "admin/updateBuffetForm.html";
//	}
	public String updateBuffet(@ModelAttribute("buffet") @Valid Buffet buffet, BindingResult bindingResult,@RequestParam(name="piattiSelezionati", required = false) List<Piatto> listaPiatti,@RequestParam(name="chefSelezionato", required = false) Long chefId, Model model) {
		
		Chef chef = null;
		if(chefId != null)
			chef = this.chefService.findById(chefId);
		buffet.setChef(chef);
		buffet.setPiatti(listaPiatti);
		this.buffetValidator.validate(buffet, bindingResult);
		if(!bindingResult.hasErrors()) {
			System.out.println("ID BUFFET :"+buffet.getId());
			buffetService.addBuffet(buffet);
			return  "admin/adminPage.html";
		}
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("piatti", this.piattoService.findAll());
		return  "admin/updateBuffetForm.html"; 
	}
	@GetMapping(value="/admin/buffetForm")
	public String addBuffet(Model model) {
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("piatti", this.piattoService.findAll());
		model.addAttribute("buffets", this.buffetService.findAll());
		return "admin/buffetForm.html";
	}


	@GetMapping(value="/buffet/{id}")
	public String getBuffet(Model model, @PathVariable("id") Long id) {
		Buffet buffet = buffetService.findById(id);
		Chef chef = buffet.getChef();
		model.addAttribute("buffet", buffet);
		model.addAttribute("chef", chef);
		return "buffet.html";
	}
	
	@GetMapping(value = "/removeBuffet/{id}")
	public String removeBuffet(Model model, @PathVariable("id") Long id) {
		this.buffetService.remove(id);
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("piatti", this.piattoService.findAll());
		model.addAttribute("buffets", this.buffetService.findAll());
		return "admin/buffetForm.html";
	}
	@GetMapping(value = "/updateBuffet/{id}")
	public String updateBuffet(Model model, @PathVariable("id") Long id) {
		Buffet buffet = this.buffetService.findById(id);
		//List<Piatto> piattiSelezionati = buffet.getPiatti();
		//model.addAttribute("piattiSelezionati", piattiSelezionati);
		System.out.println("ID BUFFET :"+buffet.getId());
		model.addAttribute("buffet", buffet);
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("piatti", this.piattoService.findAll());
		
		return "admin/updateBuffetForm.html";
	}

}
