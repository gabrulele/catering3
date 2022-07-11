package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Buffet;
import it.uniroma3.siw.model.Chef;
import it.uniroma3.siw.repository.ChefRepository;

@Service
public class ChefService {

	@Autowired
	private ChefRepository chefRepository;

	@Transactional
	public void save(Chef chef) {
		chefRepository.save(chef);
	}

	@Transactional
	public void deleteById(Long id) {
		chefRepository.deleteById(id); 
	}
	
	@Transactional
	public void update(Chef chef, Buffet buffet) {
		chef.getBuffets().add(buffet);
	}
	
	@Transactional
	public void removeBuffet(Chef chef, Buffet buffet) {
		chef.getBuffets().remove(buffet);
	}

	/* Le seguenti operazioni in lettura non è necessario
	 * che siano @Transactional,
	 * nota valida per i metodi findById e findAll
	 */
	
	public Chef findById(Long id) {
		return chefRepository.findById(id).get();
	}

	public List<Chef> findAll() {
		List<Chef> chefTotali = new ArrayList<Chef>();
		
		for(Chef chef: chefRepository.findAll()) {
			chefTotali.add(chef);
		}
		
		return chefTotali;
	}
	
	public boolean alreadyExists(Chef chef) {
		return this.chefRepository.existsChefByNomeAndCognomeAndNazionalita
				(chef.getNome(), chef.getCognome(), chef.getNazionalita());
	}

	public boolean containsNumbers(String nome, String cognome, String nazionalita) {
		for(char c: nome.toCharArray()) {
			if(Character.isDigit(c))
				return true;
		}
		for(char c: cognome.toCharArray()) {
			if(Character.isDigit(c))
				return true;
		}
		for(char c: nazionalita.toCharArray()) {
			if(Character.isDigit(c))
				return true;
		}
		return false;
	}

	public List<Chef> ordina() {
		return chefRepository.findByOrderByNomeAsc();
	}

}
