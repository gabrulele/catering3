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

import it.uniroma3.siw.controller.validator.BuffetValidator;
import it.uniroma3.siw.model.Buffet;
import it.uniroma3.siw.service.BuffetService;
import it.uniroma3.siw.service.ChefService;
import it.uniroma3.siw.service.PiattoService;

@Controller
public class BuffetController {

	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private PiattoService piattoService;
	 
	@Autowired
	private BuffetValidator buffetValidator;
	
	@GetMapping("/toDeleteBuffet/{id}")
	public String toDeleteBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		return "/buffet/toDeleteBuffet.html";
	}
	
	@GetMapping("/deleteBuffet/{id}")
	public String deleteBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = buffetService.findById(id);
		chefService.removeBuffet(buffet.getChef(), buffet);
		buffetService.deleteById(id);
		model.addAttribute("buffets", buffetService.findAll());
		return "/buffet/buffets.html";
	}
	
	@GetMapping("/toUpdateBuffet/{id}")
	public String toUpdateBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		model.addAttribute("chefs", chefService.findAll());
		model.addAttribute("piatti", piattoService.findAll());
		return "/buffet/buffetFormDiModifica.html";
	}
	
	@PostMapping("/buffet/{id}")
	public String updateBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model) {
		
		if(!bindingResult.hasErrors()) {
			buffetService.save(buffet);
			model.addAttribute("buffet", buffet);
			return "/buffet/buffet.html";
		}
		return "/buffet/buffetFormDiModifica.html";
	}
	
	@PostMapping("/buffet")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model) {
		buffetValidator.validate(buffet, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			buffetService.save(buffet);
			model.addAttribute("buffet", buffet);
			return "/buffet/buffet.html";
		}
		return "/buffet/buffetForm.html";
	}
	
	@GetMapping("/buffet")
	public String getAllBuffets(Model model) {
		List<Buffet> buffets = buffetService.findAll();
		model.addAttribute("buffets", buffets);
		return "/buffet/buffets.html";
	}

	@GetMapping("/buffet/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		return "/buffet/buffet.html";
	}
	
	@GetMapping("/buffetForm")
	public String startBuffet(Model model) {
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chefs", chefService.findAll());
		model.addAttribute("piatti", piattoService.findAll());
		return "/buffet/buffetForm.html";
	}
	
}
