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
import it.uniroma3.siw.controller.validator.IngredienteValidator;
import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.service.IngredienteService;

@Controller
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;
	 
	@Autowired
	private IngredienteValidator ingredienteValidator;
	
	@GetMapping("/toDeleteIngrediente/{id}")
	public String toDeleteIngrediente(@PathVariable("id") Long id, Model model) {
		Ingrediente ingrediente = ingredienteService.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		return "/ingrediente/toDeleteIngrediente.html";
	}
	
	@GetMapping("/deleteIngrediente/{id}")
	public String deleteIngrediente(@PathVariable("id") Long id, Model model) {
		Ingrediente ingrediente = ingredienteService.findById(id);
		ingredienteService.updateBuffets(ingrediente);
		ingredienteService.deleteById(id);
		model.addAttribute("ingredienti", ingredienteService.findAll());
		return "/ingrediente/ingredienti.html";
	}
	
	@GetMapping("/toUpdateIngrediente/{id}")
	public String toUpdateIngrediente(@PathVariable("id") Long id, Model model) {
		Ingrediente ingrediente = ingredienteService.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		return "/ingrediente/ingredienteFormDiModifica.html";
	}
	
	@PostMapping("/ingrediente/{id}")
	public String updateIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {
		ingredienteValidator.validate(ingrediente, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			ingredienteService.save(ingrediente);
			model.addAttribute("ingrediente", ingrediente);
			return "/ingrediente/ingrediente.html";
		}
		return "/ingrediente/ingredienteFormDiModifica.html";
	}
	
	@PostMapping("/ingrediente")
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {
		ingredienteValidator.validate(ingrediente, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			ingredienteService.save(ingrediente);
			model.addAttribute("ingrediente", ingrediente);
			return "/ingrediente/ingrediente.html";
		}
		return "/ingrediente/ingredienteForm.html";
	}
	
	@GetMapping("/ingrediente")
	public String getAllIngredienti(Model model) {
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "/ingrediente/ingredienti.html";
	}

	@GetMapping("/ingrediente/{id}")
	public String getIngrediente(@PathVariable("id") Long id, Model model) {
		Ingrediente ingrediente = ingredienteService.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		return "/ingrediente/ingrediente.html";
	}
	
	@GetMapping("/ingredienteForm")
	public String startIngrediente(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "/ingrediente/ingredienteForm.html";
	}
	
}
