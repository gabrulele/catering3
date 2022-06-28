package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;
import it.uniroma3.siw.model.Chef;
import it.uniroma3.siw.service.ChefService;

@Component
public class ChefValidator implements Validator {

	@Autowired
	private ChefService chefService;
	
	@Override
	public void validate(Object o, Errors errors) {
		Chef chef = (Chef) o;
		if(this.chefService.alreadyExists(chef))
			errors.reject("chef.duplicato");
	}
	
	public void validateNomeAndCognome(Object o, Errors errors) {
		Chef chef = (Chef) o;
		if(this.chefService.containsNumbers(chef.getNome(), chef.getCognome(), chef.getNazionalita()))
			errors.reject("nomeOrCognomeOrNazionalitaChef.haNumeri");
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Chef.class.equals(aClass);
	}

}
