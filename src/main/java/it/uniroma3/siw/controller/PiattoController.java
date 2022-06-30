package it.uniroma3.siw.controller;

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
import it.uniroma3.siw.controller.validator.PiattoValidator;
import it.uniroma3.siw.model.Piatto;
import it.uniroma3.siw.service.IngredienteService;
import it.uniroma3.siw.service.PiattoService;

@Controller
public class PiattoController {

	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private IngredienteService ingredienteService;
	 
	@Autowired
	private PiattoValidator piattoValidator;
	
	@GetMapping("/toDeletePiatto/{id}")
	public String toDeletePiatto(@PathVariable("id") Long id, Model model) {
		Piatto piatto = piattoService.findById(id);
		model.addAttribute("piatto", piatto);
		return "/admin/piatto/toDeletePiatto.html";
	}
	
	@GetMapping("/deletePiatto/{id}")
	public String deletePiatto(@PathVariable("id") Long id, Model model) {
		Piatto piatto = piattoService.findById(id);
		piattoService.updateBuffets(piatto);
		piattoService.deleteById(id);
		model.addAttribute("piatti", piattoService.findAll());
		return "/admin/piatto/piatti.html";
	}
	
	@GetMapping("/toUpdatePiatto/{id}")
	public String toUpdatePiatto(@PathVariable("id") Long id, Model model) {
		Piatto piatto = piattoService.findById(id);
		model.addAttribute("piatto", piatto);
		model.addAttribute("ingredienti", ingredienteService.findAll());
		return "/admin/piatto/piattoFormDiModifica.html";
	}
	
	@PostMapping("/piatto/{id}")
	public String updatePiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult, Model model) {
		
		if(!bindingResult.hasErrors()) {
			piattoService.save(piatto);
			model.addAttribute("piatto", piatto);
			return "admin/piatto/piattoAdmin.html";
		}
		return "/admin/piatto/piattoFormDiModifica.html";
	}
	
	@PostMapping("/piatto")
	public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult, Model model) {
		piattoValidator.validate(piatto, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			piattoService.save(piatto);
			model.addAttribute("piatto", piatto);
			return "admin/piatto/piattoAdmin.html";
		}
		return "/admin/piatto/piattoForm.html";
	}
	
	@GetMapping("/piatto")
	public String getAllPiatti(Model model) {
		List<Piatto> piatti = piattoService.findAll();
		model.addAttribute("piatti", piatti);
		return "/admin/piatto/piatti.html";
	}
	
	@GetMapping("/piattiUser")
	public String getAllPiattiUser(Model model) {
		List<Piatto> piatti = piattoService.findAll();
		model.addAttribute("piatti", piatti);
		return "/piatto/piattiUser.html";
	}

	@GetMapping("/piatto/{id}")
	public String getPiatto(@PathVariable("id") Long id, Model model) {
		Piatto piatto = piattoService.findById(id);
		model.addAttribute("piatto", piatto);
		return "/piatto/piatto.html";
	}
	
	@GetMapping("/piattoAdmin/{id}")
	public String getPiattoAdmin(@PathVariable("id") Long id, Model model) {
		Piatto piatto = piattoService.findById(id);
		model.addAttribute("piatto", piatto);
		return "admin/piatto/piattoAdmin.html";
	}
	
	@GetMapping("/piattoForm")
	public String startPiatto(Model model) {
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("ingredienti", ingredienteService.findAll());
		return "/admin/piatto/piattoForm.html";
	}
	
}
