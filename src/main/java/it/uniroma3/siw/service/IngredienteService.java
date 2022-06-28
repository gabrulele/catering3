package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Buffet;
import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.model.Piatto;
import it.uniroma3.siw.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Transactional
	public void save(Ingrediente ingrediente) {
		ingredienteRepository.save(ingrediente);
	}

	@Transactional
	public void deleteById(Long id) {
		ingredienteRepository.deleteById(id); 
	}

	/* Le seguenti operazioni in lettura non è necessario
	 * che siano @Transactional,
	 * nota valida per i metodi findById e findAll
	 */
	
	public Ingrediente findById(Long id) {
		return ingredienteRepository.findById(id).get();
	}

	public List<Ingrediente> findAll() {
		List<Ingrediente> ingredientiTotali = new ArrayList<Ingrediente>();
		
		for(Ingrediente ingrediente: ingredienteRepository.findAll()) {
			ingredientiTotali.add(ingrediente);
		}
		
		return ingredientiTotali;
	}
	
	public boolean alreadyExists(Ingrediente ingrediente) {
		return this.ingredienteRepository.existsIngredienteByNome
				(ingrediente.getNome());
	}

	public void updateBuffets(Ingrediente ingrediente) {
		
		for(Piatto piatto: ingrediente.getPiatti()) {
			for(Buffet buffet: piatto.getBuffets()) {
				buffet.getPiatti().remove(piatto);
			}
		}
		
	}
	
}
