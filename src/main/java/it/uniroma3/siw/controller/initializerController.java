package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.service.IngredienteService;

@Controller
public class initializerController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping("/initializer")
	public String initializer() {
		
		Ingrediente ing1 = new Ingrediente("Olio","Italia","EVO",null);
		Ingrediente ing2 = new Ingrediente("Impasto pizza","Italia","Impasto napoletano",null);
		Ingrediente ing3 = new Ingrediente("Passata","Italia","Passata di datterini",null);
		Ingrediente ing4 = new Ingrediente("Funghi","Francia","Coltivazione di serra",null);
		Ingrediente ing5 = new Ingrediente("Mozzarella","Italia","Mozzarella di bufala",null);
		Ingrediente ing6 = new Ingrediente("Prosciutto cotto","Italia","Granbiscotto",null);
		Ingrediente ing7 = new Ingrediente("Acciughe","Italia","Surgelate",null);
		Ingrediente ing8 = new Ingrediente("Fiori di zucca","Italia","KM 0",null);
		Ingrediente ing9 = new Ingrediente("Nutella","Italia","Crema spalmabile alla nocciola",null);
		
		ingredienteService.save(ing1);
		ingredienteService.save(ing2);
		ingredienteService.save(ing3);
		ingredienteService.save(ing4);
		ingredienteService.save(ing5);
		ingredienteService.save(ing6);
		ingredienteService.save(ing7);
		ingredienteService.save(ing8);
		ingredienteService.save(ing9);
		
		return "/admin/indexAdmin.html";
	}

}
