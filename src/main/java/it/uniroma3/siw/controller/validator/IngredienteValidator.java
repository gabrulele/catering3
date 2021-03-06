package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.service.IngredienteService;

@Component
public class IngredienteValidator implements Validator {

	@Autowired
	private IngredienteService ingredienteService;
	
	@Override
	public void validate(Object o, Errors errors) {
		Ingrediente ingrediente = (Ingrediente) o;
		if(this.ingredienteService.alreadyExists(ingrediente))
			errors.reject("ingrediente.duplicato");
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Ingrediente.class.equals(aClass);
	}

}
