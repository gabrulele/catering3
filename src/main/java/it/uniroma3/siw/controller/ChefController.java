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
import it.uniroma3.siw.controller.validator.ChefValidator;
import it.uniroma3.siw.model.Chef;
import it.uniroma3.siw.service.ChefService;

@Controller
public class ChefController {

	@Autowired
	private ChefService chefService;
	 
	@Autowired
	private ChefValidator chefValidator;
	
	@GetMapping("/toDeleteChef/{id}")
	public String toDeleteChef(@PathVariable("id") Long id, Model model) {
		Chef chef = chefService.findById(id);
		model.addAttribute("chef", chef);
		return "/admin/chef/toDeleteChef.html";
	}
	
	@GetMapping("/deleteChef/{id}")
	public String deleteChef(@PathVariable("id") Long id, Model model) {
		chefService.deleteById(id);
		model.addAttribute("chefs", chefService.findAll());
		return "/admin/chef/chefs.html";
	}
	
	@GetMapping("/toUpdateChef/{id}")
	public String toUpdateChef(@PathVariable("id") Long id, Model model) {
		Chef chef = chefService.findById(id);
		model.addAttribute("chef", chef);
		return "/admin/chef/chefFormDiModifica.html";
	}
	
	@PostMapping("/chef/{id}")
	public String updateChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) {
		chefValidator.validate(chef, bindingResult);
		chefValidator.validateNomeAndCognome(chef, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			chefService.save(chef);
			model.addAttribute("chef", chef);
			return "admin/chef/chefAdmin.html";
		}
		return "/admin/chef/chefFormDiModifica.html";
	}
	
	@PostMapping("/chef")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) {
		chefValidator.validate(chef, bindingResult);
		chefValidator.validateNomeAndCognome(chef, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			chefService.save(chef);
			model.addAttribute("chef", chef);
			return "admin/chef/chefAdmin.html";
		}
		return "/admin/chef/chefForm.html";
	}
	
	@GetMapping("/chef")
	public String getAllChefs(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "/admin/chef/chefs.html";
	}
	
	@GetMapping("/chefsUser")
	public String getAllChefsUser(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "/chef/chefsUser.html";
	}

	@GetMapping("/chef/{id}")
	public String getChef(@PathVariable("id") Long id, Model model) {
		Chef chef = chefService.findById(id);
		model.addAttribute("chef", chef);
		return "/chef/chef.html";
	}
	
	@GetMapping("/chefAdmin/{id}")
	public String getChefAdmin(@PathVariable("id") Long id, Model model) {
		Chef chef = chefService.findById(id);
		model.addAttribute("chef", chef);
		return "admin/chef/chefAdmin.html";
	}
	
	@GetMapping("/chefForm")
	public String startChef(Model model) {
		model.addAttribute("chef", new Chef());
		return "/admin/chef/chefForm.html";
	}
	
	@GetMapping("/ordinaChef")
	public String getAllChefsOrdinati(Model model) {
		List<Chef> chefs = chefService.ordina();
	    model.addAttribute("chefs", chefs);
		return "/admin/chef/chefs.html";
	}
	
}
